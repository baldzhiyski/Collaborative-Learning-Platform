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

    // Handle form submission
    document.getElementById('submitBtn').addEventListener('click', function () {
        // Collect University Data
        const university = {
            name: document.getElementById('universityName').value,
            description: document.getElementById('universityDescription').value,
            courses: []
        };

        // Collect Courses Data
        document.querySelectorAll('.course-card').forEach(courseCard => {
            const course = {
                name: courseCard.querySelector('.course-name').value,
                description: courseCard.querySelector('.course-description').value,
                degree: courseCard.querySelector('.course-degree').value,
                language: courseCard.querySelector('.course-language').value
            };
            university.courses.push(course);
        });

        // Send a POST to the university microservice
        console.log(university);
    });
});