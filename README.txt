Overview
This application provides basic image processing operations such as converting an image to greyscale, adjusting brightness, flipping the image, and saving the modified image.
(Update.)
Previous versions only supported the ppm image format, but this update now supports various image formats (e.g. jpg, png). In addition, the update of the MVC model adds logic for the under-filter transformation that the user wants to support.

Components
The application is based on the MVC (Model-View-Controller) design pattern, with several classes and interfaces forming the structure of the application.

Model
The ImageModel interface defines methods for the various image processing operations. The ImageModelImpl class implements this interface and provides the logic for each operation.
(Update)
In order to support various image formats, the method of managing ppm files was maintained, and filter and transformation were added as new logic to allow users to access various image formats. In addition, filter logic has been placed separately so that users can make additional changes if there is a filter value they want (refer to the enum file).

View
The ImageView interface defines methods for updating the displayed image and displaying error messages. The ImageViewImpl class implements this interface and provides a simple text-based view in the console.

Controller
The ImageController interface defines methods for loading an image, performing image processing operations, and saving the image. The ImageControllerImpl class implements this interface and coordinates the actions between the model and the view.
(Update)
To support various file formats, we modified the method of saving and loading. More types of image formats are now available. RunProgram has been added. In order to reduce the utilization of the main class and perform the role of the controller properly, we put all the commands that can control the entire program.

Main
The Main class contains the main method that starts the application. It creates instances of the model, view, and controller, and provides a simple text-based user interface for performing image processing operations.
(Update)
We sought to reduce utilization within the main class and simplify the code. You don't need to change or modify the main class separately. Use the jar application file for simple execution purposes, not modifications.

Operation
The application operates by reading an image file in PPM format, performing one or more image processing operations on the image, and then saving the modified image to a new PPM file. The user can choose the operation from a menu, and the application will display the modified image and any error messages in the console.
(Update)
Running the jar application makes the program simple. When the program runs, enter a name that contains the extension.


Image Source:
snail.ppm https://people.sc.fsu.edu/~jburkardt/data/ppma/ppma.html
snorlax.png https://pokemondb.net/artwork/snorlax
