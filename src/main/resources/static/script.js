<<<<<<< HEAD
=======
// Existing calculate function
>>>>>>> 1b724b3d0410608514cf2fce78b34614dcf3fcb6
function calculate(operation) {
    // Get input values
    const firstNumber = parseFloat(document.getElementById('firstNumber').value);
    const secondNumber = parseFloat(document.getElementById('secondNumber').value);
    const resultElement = document.getElementById('result');
<<<<<<< HEAD

=======
    
>>>>>>> 1b724b3d0410608514cf2fce78b34614dcf3fcb6
    // Check if inputs are valid numbers
    if (isNaN(firstNumber) || isNaN(secondNumber)) {
        resultElement.innerHTML = "<span class='error'>Please enter valid numbers</span>";
        return;
    }
<<<<<<< HEAD

    // Clear previous result
    resultElement.textContent = "Calculating...";

    // Set up API URL
    const baseUrl = "http://localhost:8080/api/calculator/";
    const url = `${baseUrl}${operation}?a=${firstNumber}&b=${secondNumber}`;

=======
    
    // Clear previous result
    resultElement.textContent = "Calculating...";
    
    // Set up API URL
    const baseUrl = "http://localhost:8080/api/calculator/";
    const url = `${baseUrl}${operation}?a=${firstNumber}&b=${secondNumber}`;
    
>>>>>>> 1b724b3d0410608514cf2fce78b34614dcf3fcb6
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
<<<<<<< HEAD
=======
}

// New function for percentage calculations
function calculatePercentage(operation) {
    const resultElement = document.getElementById('result');
    let url;
    let errorMessage;
    
    // Clear previous result
    resultElement.textContent = "Calculating...";
    
    // Set up API URL based on the operation
    const baseUrl = "http://localhost:8080/api/calculator/";
    
    if (operation === 'percentage') {
        const value = parseFloat(document.getElementById('percentageValue').value);
        const percent = parseFloat(document.getElementById('percentagePercent').value);
        
        if (isNaN(value) || isNaN(percent)) {
            resultElement.innerHTML = "<span class='error'>Please enter valid numbers</span>";
            return;
        }
        
        url = `${baseUrl}${operation}?value=${value}&percent=${percent}`;
        errorMessage = "Error calculating percentage";
    } 
    else if (operation === 'percentageOf') {
        const part = parseFloat(document.getElementById('percentagePart').value);
        const total = parseFloat(document.getElementById('percentageTotal').value);
        
        if (isNaN(part) || isNaN(total)) {
            resultElement.innerHTML = "<span class='error'>Please enter valid numbers</span>";
            return;
        }
        
        if (total === 0) {
            resultElement.innerHTML = "<span class='error'>Total cannot be zero</span>";
            return;
        }
        
        url = `${baseUrl}${operation}?part=${part}&total=${total}`;
        errorMessage = "Error calculating percentage";
    } 
    else if (operation === 'percentageChange') {
        const oldValue = parseFloat(document.getElementById('percentageOldValue').value);
        const newValue = parseFloat(document.getElementById('percentageNewValue').value);
        
        if (isNaN(oldValue) || isNaN(newValue)) {
            resultElement.innerHTML = "<span class='error'>Please enter valid numbers</span>";
            return;
        }
        
        if (oldValue === 0) {
            resultElement.innerHTML = "<span class='error'>Old value cannot be zero</span>";
            return;
        }
        
        url = `${baseUrl}${operation}?oldValue=${oldValue}&newValue=${newValue}`;
        errorMessage = "Error calculating percentage change";
    }
    
    // Make API call
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error: ${response.status}`);
            }
            return response.text();
        })
        .then(data => {
            // Format result for percentage calculations
            if (operation === 'percentage') {
                resultElement.textContent = `${data} (${document.getElementById('percentagePercent').value}% of ${document.getElementById('percentageValue').value})`;
            } 
            else if (operation === 'percentageOf') {
                resultElement.textContent = `${data}% (${document.getElementById('percentagePart').value} is ${data}% of ${document.getElementById('percentageTotal').value})`;
            } 
            else if (operation === 'percentageChange') {
                const changeType = parseFloat(data) >= 0 ? "increase" : "decrease";
                resultElement.textContent = `${data}% ${changeType} (from ${document.getElementById('percentageOldValue').value} to ${document.getElementById('percentageNewValue').value})`;
            }
        })
        .catch(error => {
            resultElement.innerHTML = `<span class='error'>${errorMessage}: ${error.message}</span>`;
        });
}

// Tab switching functionality
function openTab(tabName) {
    // Hide all tab contents
    const tabContents = document.getElementsByClassName('tab-content');
    for (let i = 0; i < tabContents.length; i++) {
        tabContents[i].classList.remove('active');
    }
    
    // Remove active class from all tab buttons
    const tabButtons = document.getElementsByClassName('tab-button');
    for (let i = 0; i < tabButtons.length; i++) {
        tabButtons[i].classList.remove('active');
    }
    
    // Show the selected tab content
    document.getElementById(tabName).classList.add('active');
    
    // Add active class to the clicked button
    event.currentTarget.classList.add('active');
>>>>>>> 1b724b3d0410608514cf2fce78b34614dcf3fcb6
}