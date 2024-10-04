-- Insert courses associated with universities
INSERT INTO courses (university_id, name, description, degree, language) VALUES
                                                                             -- Harvard University Courses
                                                                             ((SELECT id FROM universities WHERE name = 'Harvard University'), 'Law 101', 'Introduction to Law and Legal Systems', 'Juris Doctor', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Harvard University'), 'Constitutional Law', 'Study of the U.S. Constitution and its interpretations', 'Juris Doctor', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Harvard University'), 'Criminal Law', 'Overview of the principles of criminal law', 'Juris Doctor', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Harvard University'), 'International Law', 'Introduction to international legal systems', 'Juris Doctor', 'English'),

                                                                             -- Stanford University Courses
                                                                             ((SELECT id FROM universities WHERE name = 'Stanford University'), 'CS 101', 'Introduction to Computer Science', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Stanford University'), 'Data Structures', 'Fundamentals of data organization and manipulation', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Stanford University'), 'Artificial Intelligence', 'Introduction to Artificial Intelligence', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Stanford University'), 'Machine Learning', 'Foundational concepts of machine learning', 'Bachelor of Science', 'English'),

                                                                             -- Massachusetts Institute of Technology Courses
                                                                             ((SELECT id FROM universities WHERE name = 'Massachusetts Institute of Technology'), 'Mathematics for Computer Science', 'Discrete Mathematics and its Applications', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Massachusetts Institute of Technology'), 'Artificial Intelligence', 'Introduction to Artificial Intelligence', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Massachusetts Institute of Technology'), 'Quantum Physics', 'Exploration of the principles of quantum mechanics', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Massachusetts Institute of Technology'), 'Algorithms', 'Study of algorithms and their applications', 'Bachelor of Science', 'English'),

                                                                             -- University of Oxford Courses
                                                                             ((SELECT id FROM universities WHERE name = 'University of Oxford'), 'Philosophy of Mathematics', 'Exploring the Philosophical Foundations of Mathematics', 'Bachelor of Arts', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'University of Oxford'), 'Introduction to Artificial Intelligence', 'Fundamentals of AI concepts', 'Bachelor of Arts', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'University of Oxford'), 'Classical Mechanics', 'Overview of classical mechanics principles', 'Bachelor of Arts', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'University of Oxford'), 'Bioinformatics', 'Application of computer science in biology', 'Bachelor of Arts', 'English'),

                                                                             -- University of Cambridge Courses
                                                                             ((SELECT id FROM universities WHERE name = 'University of Cambridge'), 'History of Science', 'The Historical Development of Scientific Ideas', 'Bachelor of Arts', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'University of Cambridge'), 'Machine Learning', 'Introduction to machine learning concepts', 'Bachelor of Arts', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'University of Cambridge'), 'Physics I', 'Fundamentals of physics concepts', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'University of Cambridge'), 'Philosophy of Science', 'Exploring philosophical questions in science', 'Bachelor of Arts', 'English'),

                                                                             -- California Institute of Technology Courses
                                                                             ((SELECT id FROM universities WHERE name = 'California Institute of Technology'), 'Quantum Mechanics', 'Fundamentals of Quantum Mechanics', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'California Institute of Technology'), 'Engineering Physics', 'Physics applied to engineering problems', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'California Institute of Technology'), 'Mathematics for Engineers', 'Mathematical tools for engineering applications', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'California Institute of Technology'), 'Artificial Intelligence', 'Foundational principles of AI', 'Bachelor of Science', 'English'),

                                                                             -- ETH Zurich Courses
                                                                             ((SELECT id FROM universities WHERE name = 'ETH Zurich'), 'Data Science Fundamentals', 'Introduction to Data Science and Analysis', 'Master of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'ETH Zurich'), 'Advanced Mathematics', 'Topics in advanced mathematical methods', 'Master of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'ETH Zurich'), 'Robotics', 'Foundations of robotics and automation', 'Master of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'ETH Zurich'), 'Artificial Intelligence', 'Introduction to concepts of AI', 'Master of Science', 'English'),

                                                                             -- University of Chicago Courses
                                                                             ((SELECT id FROM universities WHERE name = 'University of Chicago'), 'Economics of Artificial Intelligence', 'The Economic Impact of AI Technologies', 'Bachelor of Arts', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'University of Chicago'), 'Financial Mathematics', 'Mathematical principles in finance', 'Bachelor of Arts', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'University of Chicago'), 'Philosophy of Science', 'Exploring philosophical questions in science', 'Bachelor of Arts', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'University of Chicago'), 'Machine Learning Fundamentals', 'Basic concepts and algorithms of ML', 'Bachelor of Arts', 'English'),

                                                                             -- Columbia University Courses
                                                                             ((SELECT id FROM universities WHERE name = 'Columbia University'), 'Applied Machine Learning', 'Practical Approaches to Machine Learning', 'Master of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Columbia University'), 'Data Mining', 'Techniques for extracting information from large datasets', 'Master of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Columbia University'), 'Statistics for Data Science', 'Statistical methods for data analysis', 'Master of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Columbia University'), 'Artificial Intelligence', 'Introduction to AI concepts', 'Master of Science', 'English'),

                                                                             -- Imperial College London Courses
                                                                             ((SELECT id FROM universities WHERE name = 'Imperial College London'), 'Engineering Mathematics', 'Mathematical techniques for engineers', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Imperial College London'), 'Introduction to Artificial Intelligence', 'Fundamentals of AI concepts', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Imperial College London'), 'Quantum Computing', 'Principles of quantum computing technologies', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Imperial College London'), 'Data Science', 'Foundational concepts of data science', 'Bachelor of Science', 'English'),

                                                                             -- Yale University Courses
                                                                             ((SELECT id FROM universities WHERE name = 'Yale University'), 'Artificial Intelligence', 'Exploration of AI methodologies', 'Bachelor of Arts', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Yale University'), 'Machine Learning', 'Overview of machine learning techniques', 'Bachelor of Arts', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Yale University'), 'Computer Science Basics', 'Introduction to fundamental computer science concepts', 'Bachelor of Arts', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'Yale University'), 'Ethics in Technology', 'Discussion on ethical implications of technology', 'Bachelor of Arts', 'English'),

                                                                             -- University of Tokyo Courses
                                                                             ((SELECT id FROM universities WHERE name = 'University of Tokyo'), 'Introduction to Data Science', 'Basics of data science and analysis', 'Bachelor of Arts', 'Japanese'),
                                                                             ((SELECT id FROM universities WHERE name = 'University of Tokyo'), 'Robotics', 'Fundamentals of robotics technologies', 'Bachelor of Arts', 'Japanese'),
                                                                             ((SELECT id FROM universities WHERE name = 'University of Tokyo'), 'Artificial Intelligence', 'Introduction to AI techniques', 'Bachelor of Arts', 'Japanese'),
                                                                             ((SELECT id FROM universities WHERE name = 'University of Tokyo'), 'Ethics in AI', 'Exploration of ethical considerations in AI', 'Bachelor of Arts', 'Japanese'),

                                                                             -- National University of Singapore Courses
                                                                             ((SELECT id FROM universities WHERE name = 'National University of Singapore'), 'Machine Learning', 'Foundational concepts of machine learning', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'National University of Singapore'), 'Big Data Analytics', 'Analyzing large data sets', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'National University of Singapore'), 'Artificial Intelligence', 'Introduction to AI and its applications', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'National University of Singapore'), 'Data Science Fundamentals', 'Introduction to data science principles', 'Bachelor of Science', 'English'),

                                                                             -- University of Toronto Courses
                                                                             ((SELECT id FROM universities WHERE name = 'University of Toronto'), 'Machine Learning', 'Fundamentals of machine learning', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'University of Toronto'), 'Statistics for Data Science', 'Statistical methods for data analysis', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'University of Toronto'), 'Deep Learning', 'Advanced topics in machine learning', 'Bachelor of Science', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'University of Toronto'), 'Artificial Intelligence', 'Introduction to artificial intelligence', 'Bachelor of Science', 'English'),

                                                                             -- National University of Singapore (Australia) Courses
                                                                             ((SELECT id FROM universities WHERE name = 'National University of Singapore'), 'Cyber Security', 'Fundamentals of cyber security concepts', 'Bachelor of Information Technology', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'National University of Singapore'), 'Human-Computer Interaction', 'Principles of designing user-friendly systems', 'Bachelor of Information Technology', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'National University of Singapore'), 'Software Engineering', 'Overview of software development processes', 'Bachelor of Information Technology', 'English'),
                                                                             ((SELECT id FROM universities WHERE name = 'National University of Singapore'), 'Data Science', 'Introduction to data science practices', 'Bachelor of Information Technology', 'English');
