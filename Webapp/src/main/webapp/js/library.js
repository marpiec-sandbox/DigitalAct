function library_initZoomSlider() {

    jQuery("#zoomSlider").slider({
        orientation: "vertical",
        range: "min",
        min: 1,
        max: Math.exp(1)*2,
        value: 1.23,
        step: 0.001,
        slide: function( event, ui ) {
            library_changePageZoom( (Math.log(ui.value)*150 + 20)/100);
        }
    });



}

function library_changePageZoom(zoom) {
    var jqImage = jQuery("#documentImage");
    if(jqImage.data("width") == undefined) {
        jqImage.data("width", jqImage.width());
        jqImage.data("height", jqImage.height());
    }

    var orgWidth = jqImage.data("width");
    var orgHeight = jqImage.data("height");

    jqImage.width(orgWidth * zoom);
    jqImage.height(orgHeight * zoom);

}

function library_initLibrary(pageList) {

    for (var p=0; p<pageList.length; p++) {
        library_addPage(pageList[p]);
    }

    library_showFirstPage();
}

function library_showFirstPage() {
    jQuery(".library .pagesList .pageThumbnail1").click();
}

function library_addPage(pageInfo) {
    var jqPagesList = jQuery(".library .pagesList");

    var jqPage = jQuery("<a class='pageThumbnail pageThumbnail"+pageInfo.number+"'>"+pageInfo.number+"</a>");
    jqPagesList.append(jqPage);

    jqPage.click(function() {
        var jqImage = jQuery("<img alt='"+pageInfo.name+"' src='"+pageInfo.url+"' id='documentImage' />");
        var jqImageContainer = jQuery(".library .pagePreview .documentImage");
        jqImageContainer.empty();
        jqImageContainer.append(jqImage);

        jqImage.draggable();

        jQuery(".pageThumbnail").removeClass("selected");
        jQuery(this).addClass("selected");

        library_initZoomSlider();
    });
}
