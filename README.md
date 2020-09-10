# Foury

Demo app showcasing discrete fourier transform for image manipulation. It generates magnitude image and allows to compare selections both in spatial and frequency domains.

## Technical notes
Image calculations, including DFT are done using OpenCV library. Graphical interface is implemented in JavaFx. Supports `.png` and `.jpg` image file formats.

## Build instructions
To generate jar file run:
```
gradle shadowJar
```
Requires Java 11 or higher.
