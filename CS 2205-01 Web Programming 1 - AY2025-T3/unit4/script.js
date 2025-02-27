document.getElementById("convertBtn").addEventListener("click", function () {
  let tempInput = document.getElementById("temperature").value;
  let conversionType = document.getElementById("conversionType").value;
  let resultDisplay = document.getElementById("result");

  if (isNaN(tempInput) || tempInput === "") {
    resultDisplay.textContent = "Please enter a valid number.";
    resultDisplay.style.color = "red";
    return;
  }

  let temperature = parseFloat(tempInput);
  let convertedTemp;
  let unit;

  if (conversionType === "CtoF") {
    convertedTemp = (temperature * 9) / 5 + 32;
    unit = "°F";
    resultDisplay.textContent = `${temperature} °C is ${convertedTemp.toFixed(2)} ${unit}`;
  } else {
    convertedTemp = ((temperature - 32) * 5) / 9;
    unit = "°C";
    resultDisplay.textContent = `${temperature} °F is ${convertedTemp.toFixed(2)} ${unit}`;
  }

  resultDisplay.style.color = "black";
});
