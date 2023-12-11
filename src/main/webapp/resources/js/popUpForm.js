document.getElementById("openFormBtn").addEventListener("click", openForm);

function openForm() {
    document.getElementById("mapPointForm").style.display = "block";
}

function closeForm() {
    document.getElementById("mapPointForm").style.display = "none";
}