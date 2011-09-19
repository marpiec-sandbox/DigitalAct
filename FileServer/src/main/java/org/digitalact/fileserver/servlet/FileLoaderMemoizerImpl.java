package org.digitalact.fileserver.servlet;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Implementacja fileLoadera dodająca zapamiętywanie wczytanego pliku w ehcache.
 * Jest ThreadSafe (napisana na podstawie książki "Java concurrency in practice" January 2009.
 * Strona 108.
 */
public class FileLoaderMemoizerImpl implements FileLoader {

    private final Ehcache cache;

    private FileLoader fileLoader;

    /**
     * Konstruktor.
     * @param fileLoader obiekt wczytujacy pliki
     * @param cache instancja ehcache do wykorzystania
     */
    public FileLoaderMemoizerImpl(FileLoader fileLoader, Ehcache cache) {
        this.fileLoader = fileLoader;
        this.cache = cache;
    }

    @Override
    public FileData loadFile(final String absoultePath) {

        //Petla jest, gdyby wystąpił InterruptedException
        while(true) {
            //Tutaj wyciagamy z cacha przyszlość :)

            Element cachedElement = cache.get(absoultePath);
            Future<FileData> fileDataFuture;

            //Jeżeli przyszłość nie jest zdefiniowana, to ją tworzymy
            if (cachedElement == null) {

                //Metoda faktycznie wszttujaca plik
                Callable<FileData> callable = new Callable<FileData>() {
                    @Override
                    public FileData call() {
                        return fileLoader.loadFile(absoultePath);
                    }
                };

                //Tworzymy przyszłe zadanie
                FutureTask<FileData> futureTask = new FutureTask<FileData>(callable);
                //Wkładamy do cache'a, jeżeli jeszcze go nie ma
                Element element = cache.putIfAbsent(new Element(absoultePath, futureTask));
                if (element == null) {
                    //Jeżeli go nie było to odpalamy zadanie
                    futureTask.run();
                    fileDataFuture = futureTask;
                } else {
                    //Jeżeli był to pobieramy
                    fileDataFuture = (FutureTask<FileData>) element.getObjectValue();
                }
            } else {
                fileDataFuture = (Future<FileData>) cachedElement.getObjectValue();
            }

            try {
                //Zwracamy wartość z przyszłości
                return fileDataFuture.get();
            } catch (InterruptedException e) {
                cache.remove(absoultePath);
            } catch (ExecutionException e) {
                throw new IllegalStateException("Exception in file cache", e);
            }
        }

    }
}
