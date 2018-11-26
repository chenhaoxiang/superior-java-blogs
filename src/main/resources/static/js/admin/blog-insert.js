var markdownLocal = "markdownLocalStorage";

function saveToLocal() {
    var content = $("#markdown").val();
    console.log("保存到本地，通过cookie保存文章,content=" + content);
    LocalStorageUtil.setItem(markdownLocal, content);
}

function cleanMarkDown() {
    cleanMarkDownRemoveItem();
    $("#markdown").text("");
}

function cleanMarkDownRemoveItem() {
    LocalStorageUtil.removeItem(markdownLocal);
}
