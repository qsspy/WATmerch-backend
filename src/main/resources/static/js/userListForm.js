console.log("js linked!")

function changePage(page) {

    document.getElementById("pageNumber").value = page
    document.getElementById("mainForm").submit()
}

function changeSize(size) {

    document.getElementById("pageSize").value = size
    document.getElementById("pageNumber").value = 1
    document.getElementById("mainForm").submit()
}

function filter() {
    document.getElementById("pageNumber").value = 1
    document.getElementById("mainForm").submit()
}