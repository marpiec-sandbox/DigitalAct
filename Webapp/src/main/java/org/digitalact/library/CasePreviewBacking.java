package org.digitalact.library;

import org.digitalact.configuration.WebappConfiguration;
import org.digitalact.domain.documents.entity.DocumentPage;
import org.digitalact.domain.documents.query.DocumentPageQuery;
import org.digitalact.utils.MyJsonSerializer;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * BackingBean obsługujący podgląd sprawy.
 */
@Named
@Scope("request")
public class CasePreviewBacking {

    @Inject
    private WebappConfiguration webappConfiguration;

    @Inject
    private DocumentPageQuery documentPageQuery;

    private List<PageInfo> getPageList() {

        List<DocumentPage> pages = documentPageQuery.findPagesForAct("A");

        List<PageInfo> pageInfoList = new ArrayList<PageInfo>();

        for(DocumentPage documentPage: pages) {
            pageInfoList.add(createPageInfo(documentPage));
        }
        return pageInfoList;
    }

    private PageInfo createPageInfo(DocumentPage documentPage) {
        return new PageInfo("Nazwa strony", "1", webappConfiguration.getFileServerAddress(0)+"?"+
        "f="+documentPage.getFileLocation()+"&c="+documentPage.getFileChecksum());
    }

    public String getPagesListJson() {
        return MyJsonSerializer.serialize(getPageList());
    }

}
