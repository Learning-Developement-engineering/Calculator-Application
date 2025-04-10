function calculate(operation) {
    // Get input values
    const firstNumber = parseFloat(document.getElementById('firstNumber').value);
    const secondNumber = parseFloat(document.getElementById('secondNumber').value);
    const resultElement = document.getElementById('result');

    // Check if inputs are valid numbers
    if (isNaN(firstNumber) || isNaN(secondNumber)) {
        resultElement.innerHTML = "<span class='error'>Please enter valid numbers</span>";
        return;
    }

    // Clear previous result
    resultElement.textContent = "Calculating...";

    // Set up API URL
    const baseUrl = "http://localhost:8080/api/calculator/";
    const url = `${baseUrl}${operation}?a=${firstNumber}&b=${secondNumber}`;

    // Make API call
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error: ${response.status}`);
            }
            return response.text();
        })
        .then(data => {
            resultElement.textContent = data;
        })
        .catch(error => {
            if (operation === 'divide' && secondNumber === 0) {
                resultElement.innerHTML = "<span class='error'>Cannot divide by zero</span>";
            } else {
                resultElement.innerHTML = `<span class='error'>Error: ${error.message}</span>`;
            }
        });
}