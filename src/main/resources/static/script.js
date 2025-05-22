//function callApi() {
//      fetch("/check", {
//        method: "GET",
//        headers: {
//          "X-API-KEY": "test-key"
//        }
//      })
//      .then(response => response.text())
//      .then(data => {
//        document.getElementById("response").innerText = data;
//      })
//      .catch(error => {
//        console.error("Error:", error);
//        document.getElementById("response").innerText = "Error: " + error;
//      });
//}

function checkWithAlgo(){
    fetch("/check/algo", {
                  method: "GET",
                  headers: {
                    "X-API-KEY": "test-key"
                  }
                })
                .then(response => response.text())
                .then(data => {
                  if(data == -1){
                    document.getElementById("bucketSize").innerText = "Request Throttled!!";
                  }
                  else document.getElementById("bucketSize").innerText = data;
                })
                .catch(error => {
                  console.error("Error:", error);
                  document.getElementById("response").innerText = "Error: " + error;
    });
}

function getAlgorithm(){
    fetch("/check/algo/algorithm", {
                  method: "GET",
                  headers: {
                    "X-API-KEY": "test-key"
                  }
                })
                .then(response => response.text())
                .then(data => {
                  document.getElementById("algorithmName").innerText = data;
                  console.log("Hello");
                })
                .catch(error => {
                  console.error("Error:", error);
                  document.getElementById("response").innerText = "Error: " + error;
    });
}
function onAlgorithmChange() {
        const selectedPage = document.getElementById("algorithmSelect").value;
        window.location.href = selectedPage;
}

function goToHome(){
    window.location.href = "index.html";
}

document.addEventListener("DOMContentLoaded", getAlgorithm);
document.addEventListener("DOMContentLoaded", checkWithAlgo);

