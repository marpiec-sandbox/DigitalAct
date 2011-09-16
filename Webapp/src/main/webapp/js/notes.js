window.notes_const = new Object();

notes_const.NOTE_SELECTOR = ".stickyNote";
notes_const.NOTES_CONTAINER_SELECTOR = ".notesBoard";
notes_const.CREATED_NOTE_CONTAINER_SELECTOR = ".createdNoteContainer";
notes_const.DATA_SELECTOR = ".data";
notes_const.ID_SELECTOR = ".id";
notes_const.SAVE_NOTE_SERVICE = "ajax/save-note";


function notes_initNotes() {
    var jqNotesContainer = jQuery(notes_const.NOTES_CONTAINER_SELECTOR);
    var jqNotes = jqNotesContainer.find(notes_const.NOTE_SELECTOR);
    
    jqNotes.each(function() {
        notes_initNote(jQuery(this));
    });
}

function notes_initNote(jqNote) {
    
    var data = jsonToObject(jqNote.find(notes_const.DATA_SELECTOR).val());
    jqNote.css({
        top:data.top,
        left:data.left,
        width:data.width,
        height:data.height
    });

    jQuery(function(){
          jqNote.resizable({
              handles: 'n, e, s, w, ne, nw, se, sw',
              start: notes_moveNoteOnTop,
              stop: saveNoteAfterMove
          }).draggable({
              start: notes_moveNoteOnTop,
              stop: saveNoteAfterMove
          });

         notes_initEditor(jqNote);

//        jqNote.mousedown(notes_moveNoteOnTop);
        jqNote.dblclick(notes_showNoteEditor);

    });


}

function notes_initEditor(jqNote) {
    var jqEditor = jqNote.find(".editor");
    var jqTextarea = jqEditor.find("textarea");
    jqTextarea.blur(notes_saveAndHideEditor);
}

function notes_saveAndHideEditor() {
    var jqTextarea = jQuery(this);
    var jqEditor = jqTextarea.parents(".editor");
    var jqNote = jqEditor.parents(notes_const.NOTE_SELECTOR);
    var jqText = jqNote.find(".text");
    jqText.html(jqTextarea.val());
    jqEditor.hide();
    saveNote(jqNote);
}

function notes_showNoteEditor() {
    var jqNote = jQuery(this);
    var jqEditor = jqNote.find(".editor");
    var jqTextarea = jqEditor.find("textarea");
    jqEditor.show();
    jqTextarea.focus();
}

function notes_moveNoteOnTop() {
    var jqNotesContainer = jQuery(notes_const.NOTES_CONTAINER_SELECTOR);
    jQuery(this).appendTo(jqNotesContainer);
}



function notes_initCreatedNote(newNoteContainerSelector, notesContainerSelector) {
    var jqNote = jQuery(newNoteContainerSelector).find(notes_const.NOTE_SELECTOR);
    var jqNotesContainer = jQuery(notesContainerSelector);
    jqNote.appendTo(jqNotesContainer);
    
    notes_initNote(jqNote);
}

/* Ajax */

function notes_handleAjaxCreateNote(data) {
    if (data.status == "success") {
        notes_initCreatedNote(notes_const.CREATED_NOTE_CONTAINER_SELECTOR,
            notes_const.NOTES_CONTAINER_SELECTOR);
    }
}

function saveNoteAfterMove(event, ui) {
    var jqNote = jQuery(event.target);
    saveNote(jqNote);
}


function saveNote(jqNote) {
    var data = createNoteSaveData(jqNote);
    var noteId = retriveNoteId(jqNote);

    jQuery.post(notes_const.SAVE_NOTE_SERVICE, {
        data: JSON.stringify(data),
        id: noteId
    });
}

function createNoteSaveData(jqNote) {
    var data = new Object();
    data.text = jqNote.find(".editor textarea").val();
    data.top = parseInt(jqNote.css("top"));
    data.left = parseInt(jqNote.css("left"));
    data.width = jqNote.width();
    data.height = jqNote.height();
    data.color = "red";
    return data;
}

function retriveNoteId(jqNote) {
    return jqNote.find(notes_const.ID_SELECTOR).val();
}