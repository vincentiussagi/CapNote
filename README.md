# NoCap🎓 - Note Capture

### App Description
Many students  To address this issue, our team aims to develop a mobile application named "" that can automatically convert slides or board photos into editable notes. Using machine learning techniques, the app will recognize and extract text from images, formatting and organizing them into easily accessible and editable notes for students.

The "NoCap🎓" mobile application is designed for students who face the problem of not being able to take notes or understand the lecture content when the lecturer changes the slide or board too fast.

## Machine Learning
### Model Development
### Flask Integration Details
As Cloud Computing requested, the ML team would build a a Flask web application that serves a simple OCR (Optical Character Recognition) model. It uses a pre-trained deep-learning model that ML build before to recognize text in an image.

#### Library Used
* Flask: A web framework for Python.
* cv2 (OpenCV): Computer vision library for image and video processing.
* numpy: A library for numerical operations in Python.
* matplotlib: Used for creating visualizations (not essential for the main OCR functionality).
* pickle: Used for serializing and deserializing Python objects.
* tensorflow (tf): Deep learning library for training and deploying machine learning models.

#### Flask Application Setup
An instance of the Flask web application is created with the name app.

#### Model and Class Names Loading
* A pre-trained OCR model (loaded_model) is loaded using TensorFlow's Keras API from the file 'OCRmodel.h5'.
* The class names used in the model are loaded from a pickled file ('class_names.pkl').

#### Flask Routes
1. '/predict' Route
- Accepts POST and GET requests.
- Expects an image file in the request.
- Reads the image file, converts it to a NumPy array (nparr), and decodes it using OpenCV.
- Applies image preprocessing steps, including converting to grayscale and thresholding.
- Extracts text regions using contour detection.
- Processes each segmented region, normalizes, and reshapes it to match the input size of the loaded model.
- Makes predictions using the loaded model for each segmented region.
- Assembles the predicted labels into a single string (predicted_text) and returns it as a JSON response.
2. '/' Route
- Displays 'Hello World' when accessing the root URL.

#### Running the Application
-  he app.run(debug=True) line runs the Flask application in debug mode.

#### How to Use
-  Send a POST or GET request to the '/predict' endpoint with an image file attached.
-  The server processes the image, recognizes text regions, predicts the content of each region using the loaded OCR model, and returns the predicted text as a JSON response.

#### Important Notes:
-  Ensure that the required libraries are installed (Flask, cv2, numpy, matplotlib, pickle, tensorflow).
-  The paths for the loaded model ('OCRmodel.h5') and class names ('class_names.pkl') should be accurate and accessible.
-  The preprocessing steps, model architecture, and class names should be consistent with the model used during training.


## Mobil Development
### Main Feature
The app provides the following features for the user:
* Firebase Google Authorization and authentication
  - we included firebase login using google sign in method to enhance experience and simplify user authorization 
* Add new topic
  - this is the main key of our app, using trained ML that we create on scratch, and deploy it as web service (API) to recognize text on submited picture
* Take picture using camera
  - this is an addtional feature to sumbit picture of our APP
* local databases
    - using RoomDb library as part of android jetpack to saving topic or data user in our APP

## Software and hardware requirements
The app is designed to run on mobile devices running Android 10.0 or later.

## Installing the app
To install the app, download the .apk file in the releases section.

# Working with the "NoCap🎓" app
## Running the app
To launch the app, click on the corresponding icon, which is located in the menu of the mobile device. Clicking on this icon opens an authorization window in which the user needs to enter an Google account and password.

After already logging in with a Google account, the user can fully access the app.
