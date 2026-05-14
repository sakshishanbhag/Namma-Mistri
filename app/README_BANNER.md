How to replace the banner placeholder with your image

1) Place the provided image file into:

   app/src/main/res/drawable/

   and name it `banner_wall.jpg` (or `banner_wall.png`).

2) If you add a raster image (jpg/png), Android will automatically pick it up. The layout already references `@drawable/banner_wall` in `fragment_calculator.xml`.

3) If you want different densities, add versions under `drawable-mdpi`, `drawable-xhdpi`, etc., using the same filename.

4) Rebuild the app. The banner will appear at the top of the calculator card.

Note: I added a simple gradient placeholder `res/drawable/banner_wall.xml` so the screen shows a banner even before you add the real image.