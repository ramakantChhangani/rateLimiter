function callApi() {
      fetch("/check", {
        method: "GET",
        headers: {
          "X-API-KEY": "test-key"
        }
      })
      .then(response => response.text())
      .then(data => {
        document.getElementById("response").innerText = data;
      })
      .catch(error => {
        console.error("Error:", error);
        document.getElementById("response").innerText = "Error: " + error;
      });
}