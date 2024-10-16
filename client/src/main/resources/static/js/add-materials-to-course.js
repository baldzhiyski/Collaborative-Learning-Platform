function toggleInputFields() {
    const resourceType = document.getElementById('resourceType').value;
    const youtubeUrlDiv = document.getElementById('youtubeUrlDiv');
    const documentDiv = document.getElementById('documentDiv');

    if (resourceType === 'YOUTUBE') {
        youtubeUrlDiv.style.display = 'block';
        documentDiv.style.display = 'none';
    } else if (resourceType === 'DOCUMENT') {
        youtubeUrlDiv.style.display = 'none';
        documentDiv.style.display = 'block';
    } else {
        youtubeUrlDiv.style.display = 'none';
        documentDiv.style.display = 'none';
    }
}

function updateResourceList(materials) {
    const resourceList = document.querySelector('.resource-list ul');
    resourceList.innerHTML = ''; // Clear existing list

    materials.forEach(material => {
        const listItem = document.createElement('li');
        listItem.innerHTML = `
            <i class="fas fa-file-alt resource-icon"></i>
            <div class="resource-title">${material.description}</div>
            <div class="resource-title">${'Type of file - ' + material.resourceType}</div>
            ${material.resourceType !== 'VIDEO' ?
            `<button class="btn btn-dark" id="downloadFile">Download</button>` :
            `<a class="btn btn-danger text-white" th:href="${material.youtubeUrl}" target="_blank">Go to YouTube</a>`}
        `;
        resourceList.appendChild(listItem);
    });
}

async function fetchResources(courseUuid) {

    try {
        const response = await fetch(`http://localhost:8080/resources/${courseUuid}`);
        if (response.ok) {
            const materials = await response.json();
            console.log(materials);
            if (materials) {
                updateResourceList(materials);
            }
        } else {
            console.error('Failed to fetch resources');
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

document.getElementById('resourceForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const errorMessageDiv = document.getElementById('error-message');
    errorMessageDiv.style.display = 'none'; // Hide previous error messages
    errorMessageDiv.textContent = ''; // Clear previous error messages

    const loggedInUser = document.querySelector('meta[name="loggedUser"]').getAttribute('content');
    const courseUuid = document.querySelector('meta[name="courseUUID"]').getAttribute('content');

    // Gather input values
    const description = document.querySelector('input[name="description"]').value;
    const resourceType = document.querySelector('select[name="resourceType"]').value; // Assuming a select element for resource type
    const youtubeUrl = document.querySelector('input[name="youtubeUrl"]').value;
    const fileInput = document.querySelector('input[name="multipartFile"]');
    const file = fileInput.files[0]; // Get the selected file


    // Validate required fields
    if (!description) {
        errorMessageDiv.textContent = 'Description is required.';
        errorMessageDiv.style.display = 'block';
        return; // Exit the function if validation fails
    }

    if (resourceType === 'YOUTUBE') {
        if (!youtubeUrl) {
            errorMessageDiv.textContent = 'YouTube URL is required.';
            errorMessageDiv.style.display = 'block';
            return; // Exit the function if validation fails
        }

        // Regular expression to validate YouTube URL
        const youtubeUrlRegex = /^(https?:\/\/)?(www\.)?(youtube\.com\/(watch\?v=|embed\/|v\/|.+\?v=)|youtu\.be\/)([a-zA-Z0-9_-]{11})$/;

        if (!youtubeUrlRegex.test(youtubeUrl)) {
            errorMessageDiv.textContent = 'Invalid YouTube URL format.';
            errorMessageDiv.style.display = 'block';
            return; // Exit the function if validation fails
        }
    }

    if (resourceType === 'DOCUMENT' && !file) {
        errorMessageDiv.textContent = 'File is required for document resources.';
        errorMessageDiv.style.display = 'block';
        return; // Exit the function if validation fails
    }


    // Create a FormData object
    const formData = new FormData();
    formData.append("description", description);
    formData.append("resourceType", resourceType);
    if (youtubeUrl) {
        formData.append("youtubeUrl", youtubeUrl);
    }
    if (file) {
        formData.append("multipartFile", file); // Append the file object directly
    }
    // Log FormData entries to see what is captured
    const jsonObject = {};
    for (let pair of formData.entries()) {
        // Check if the key already exists in the object
        if (jsonObject.hasOwnProperty(pair[0])) {
            // If it does, convert it to an array and push the new value
            if (!Array.isArray(jsonObject[pair[0]])) {
                jsonObject[pair[0]] = [jsonObject[pair[0]]];
            }
            jsonObject[pair[0]].push(pair[1]);
        } else {
            // If it doesn't, simply set the value
            jsonObject[pair[0]] = pair[1];
        }
    }
    console.log(jsonObject)

    try {
        const response = await fetch(`http://localhost:8080/resources/upload/${loggedInUser}/user/${courseUuid}`, {
            method: 'POST',
            headers: {
                'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content'), // Fetch CSRF token
                // Do NOT set 'Content-Type' when using FormData
            },
            body: formData, // Send FormData, which includes the file
        });


        if (response.ok) {
            alert('Resource uploaded successfully');
            await fetchResources(courseUuid); // Fetch updated resources
        } else {
            alert('Failed to upload resource');
        }
    } catch (error) {
        console.error('Error:', error);
    }
});

// Fetch resources on page load
document.addEventListener('DOMContentLoaded', () => {
    const courseUuid = document.querySelector('meta[name="courseUUID"]').getAttribute('content');

    fetchResources(courseUuid); // Fetch resources for the course when the page loads
});