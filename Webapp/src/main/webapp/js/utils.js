function jsonToObject(json) {
    try {
        return eval('(' + json + ')');
    } catch(e) {
        alert("JSON error: "+json);
    }
}