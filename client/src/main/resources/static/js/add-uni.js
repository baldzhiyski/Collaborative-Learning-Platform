// Function to show error messages in a specific container
function showError(message, containerId) {
    const errorMessageContainer = document.getElementById(containerId);
    errorMessageContainer.innerHTML = message; // Set the error message
    errorMessageContainer.style.display = 'block'; // Make the container visible
}

// Function to hide error messages
function hideError(containerId) {
    const errorMessageContainer = document.getElementById(containerId);
    errorMessageContainer.style.display = 'none'; // Hide the container
}

// Function to validate course inputs
function validateCourses() {
    const courses = [];
    const courseCards = document.querySelectorAll('.course-card');
    const coursesErrorContainerId = 'coursesErrorMessageContainer'; // ID of the courses error container

    // Clear previous course error messages
    hideError(coursesErrorContainerId);

    for (const courseCard of courseCards) {
        const courseName = courseCard.querySelector('.course-name').value.trim();
        const courseDescription = courseCard.querySelector('.course-description').value.trim();
        const courseDegree = courseCard.querySelector('.course-degree').value.trim();
        const courseLanguage = courseCard.querySelector('.course-language').value.trim();

        // Check if any course fields are empty
        if (!courseName || !courseDescription || !courseDegree || !courseLanguage) {
            showError('Please fill in all required fields for each course.', coursesErrorContainerId);
            return null; // Prevent form submission
        }

        // If valid, add course to the array
        courses.push({
            name: courseName,
            description: courseDescription,
            degree: courseDegree,
            language: courseLanguage
        });
    }
    return courses; // Return the validated courses
}

// Function to submit the form
async function submitForm() {
    // Clear previous error messages
    const universityErrorContainerId = 'universityErrorMessageContainer';
    hideError(universityErrorContainerId);

    // Collect University Data
    const universityName = document.getElementById('universityName').value.trim();
    const universityDescription = document.getElementById('universityDescription').value.trim();

    // Check if the fields are empty
    if (!universityName || !universityDescription) {
        showError('Please fill in all required fields.', universityErrorContainerId);
        return; // Prevent form submission
    }

    try {
        // Check if the university already exists
        const exists = await checkUniversityExists(universityName);
        console.log(exists);
        if (exists) {
            showError('A university with this name already exists.', universityErrorContainerId);
            return; // Stop submission if university exists
        }

        // Validate courses
        const courses = validateCourses();
        if (!courses || courses.length === 0) {
            showError('Please validate your courses.', universityErrorContainerId);
            return; // Prevent submission if courses validation fails
        }

        // Prepare the university object for submission
        const university = {
            name: universityName,
            description: universityDescription,
            courses: courses // Include validated courses
        };

        console.log('University object to submit:', university);

        // Send a POST request to create the university
        const response = await fetch('http://localhost:8081/universities/publish', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content') // Fetch CSRF token
            },
            body: JSON.stringify(university)
        });

        // Handle the response
        if (!response.ok) {
            throw new Error(`Failed to create university: ${response.status} ${response.statusText}`);
        }

        const data = await response.json();
        console.log('Success:', data);
        // Redirect or display a success message here if needed
        window.location.href = '/';

    } catch (error) {
        console.error('Error:', error.message); // Log specific error messages
        showError('Failed to submit the university data.', universityErrorContainerId); // Show an error message on failure
    }
}

// Function to check if the university exists
async function checkUniversityExists(universityName) {
    console.log('Checking if university exists:', universityName);
    const response = await fetch(`http://localhost:8081/universities/find/${universityName}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content') // Fetch CSRF token
        },
    });

    if (response.ok) {
        return true; // University exists
    } else if (response.status === 400 || response.status === 404) {
        return false; // University does not exist
    } else {
        throw new Error(`Network error: ${response.status}`); // Handle other errors
    }
}

// Add event listeners on DOMContentLoaded
document.addEventListener('DOMContentLoaded', function () {
    const addCourseBtn = document.getElementById('addCourseBtn');
    const coursesContainer = document.getElementById('coursesContainer');
    const courseTemplate = document.getElementById('courseTemplate').innerHTML;

    // Function to add a new course form
    addCourseBtn.addEventListener('click', function () {
        coursesContainer.insertAdjacentHTML('beforeend', courseTemplate);
    });

    // Event delegation for removing course
    coursesContainer.addEventListener('click', function (e) {
        if (e.target.classList.contains('remove-course-btn')) {
            e.target.closest('.course-card').remove();
        }
    });

    // Submit button click event
    document.getElementById('submitBtn').addEventListener('click', function(event) {
        event.preventDefault(); // Prevent default form submission
        submitForm();
    });
});
