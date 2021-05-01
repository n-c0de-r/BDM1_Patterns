import ij.ImageJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

//erste Uebung (elementare Bilderzeugung)

public class GLDM_U1 implements PlugIn {

	final static String[] choices = { "Schwarzes Bild", "Gelbes Bild", "Belgische Fahne", "Schwarz/Weiss Verlauf",
			"Horiz. Schwarz/Rot vert. Schwarz/Blau Verlauf", "USA Fahne", "Tschechische Fahne" };

	private String choice;

	public static void main(String args[]) {
		ImageJ ij = new ImageJ(); // neue ImageJ Instanz starten und anzeigen
		ij.exitWhenQuitting(true);

		GLDM_U1 imageGeneration = new GLDM_U1_s0577683();
		imageGeneration.run("");
	}

	public void run(String arg) {

		int width = 566; // Breite
		int height = 400; // Hoehe

		// RGB-Bild erzeugen
		ImagePlus imagePlus = NewImage.createRGBImage("GLDM_U1", width, height, 1, NewImage.FILL_BLACK);
		ImageProcessor ip = imagePlus.getProcessor();

		// Arrays fuer den Zugriff auf die Pixelwerte
		int[] pixels = (int[]) ip.getPixels();

		dialog();

		////////////////////////////////////////////////////////////////
		// Hier bitte Ihre Aenderungen / Erweiterungen

		if (choice.equals("Schwarzes Bild")) {
			generateBlackImage(width, height, pixels);
		}
		// Ab hier eigentlich Copy-Paste und Anpassung, quasi "eigener" Code ;)
		if (choice.equals("Gelbes Bild")) {
			generateYellowImage(width, height, pixels);
		}

		if (choice.equals("Belgische Fahne")) {
			generateBelgianFlag(width, height, pixels);
		}

		if (choice.equals("Schwarz/Weiss Verlauf")) {
			generateBWGradient(width, height, pixels);
		}

		if (choice.equals("Horiz. Schwarz/Rot vert. Schwarz/Blau Verlauf")) {
			generateRBGradient(width, height, pixels);
		}

		if (choice.equals("USA Fahne")) {
			generateUSAFlag(width, height, pixels);
		}

		if (choice.equals("Tschechische Fahne")) {
			generateCzechFlag(width, height, pixels);
		}

		////////////////////////////////////////////////////////////////////

		// neues Bild anzeigen
		imagePlus.show();
		imagePlus.updateAndDraw();
	}

	private void generateBlackImage(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				/* Da alle Werte 0 sind, besteht kein Grund sie hier
				 * in einzelne Variablen zu speichern. Diese Werte 
				 * werden nicht weiter verändert, daher unnötig
				 * hier Variablen zu nutzen und vorzuhalten. Redundanz.*/
				 // Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (0 << 16) | (0 << 8) | 0;
			}
		}
	}

	private void generateYellowImage(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				
				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (255 << 16) | (255 << 8) | 0;
			}
		}
	}

	//Farbcodes gemäß https://www.schemecolor.com/belgium-flag-colors.php
	private void generateBelgianFlag(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				
				if (x < width / 3) {
					/* Wenn der Pixel im ersten vertikalen Drittel ist
					 * (also links), dann schwarz einfärben.*/
					pixels[pos] = 0xFF000000 | (0 << 16) | (0 << 8) | 0;
				
				} else if (x > (width / 3) * 2) {
					/* Wenn der Pixel im letzten vertikalen Drittel ist
					 * (also rechts), dann rot einfärben.*/
					pixels[pos] = 0xFF000000 | (239 << 16) | (51 << 8) | 64;

				} else {
					//Ansonsten den Rest (Mitte) gelb färben.
					pixels[pos] = 0xFF000000 | (253 << 16) | (218 << 8) | 36;

				}
			}
		}
	}

	private void generateBWGradient(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				/* Die x Werte mit 1.0 multiplizieren,
				 * um Double-Werte zu erzeugen, für eine präzise
				 * Berechnung der Mischfarbwerte.
				 * Horizontalwerte in Verhältnis zur Breite setzen.*/
				int val = (int) ((1.0 * x / width) * 255);
				// Die Double werte in Integer casten und darstellen.
				pixels[pos] = 0xFF000000 | (val << 16) | (val << 8) | val;
			}
		}
	}

	private void generateRBGradient(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				/* jeweiligen x & y Wert mit 1.0 multiplizieren,
				 * um Double-Werte zu erzeugen, für eine präzise
				 * Berechnung der Mischfarbwerte.*/
				int red = (int) ((1.0 * x / width) * 255);
				
				/* Da es kein Grün gibt, wird es nicht
				 * berechnet, nur auf 0 gesetzt*/
				int blue = (int) ((1.0 * y / height) * 255);
				// Die Double werte in Integer casten und darstellen.
				pixels[pos] = 0xFF000000 | (red << 16) | (0 << 8) | red;
			}
		}
	}

	// Farbcodes gemäß https://www.schemecolor.com/united-states-of-america-flag-colors.php
	private void generateUSAFlag(int width, int height, int[] pixels) {
		// Höhe eines Streifens als Double berechnen; 13tel der Gesamthöhe
		double stripes = (height / 13.0);
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				
				/* Wenn aktueller Pixel höher ist als der 8. Streifen
				 * bzw weiter links als 40% der Gesamtbreite, dann
				 * wird der Bereich gemäß den Standardmaßen 
				 * in der Frabe "American Blue" gefärbt.*/
				if (1.0 * y < (stripes) * 7 & x < width * 0.4) {
					/* Da ein Streifen eigentlich 30,7px hoch sind, noch 1.0 * dazu
					 * im eine Typenumwandlung vorzunehmen, für präzise Rechnung.*/
					pixels[pos] = 0xFF000000 | (60 << 16) | (59 << 8) | 110;

				} else {
					if (1.0 * y % (2*stripes) >= 30.7) {
						
						pixels[pos] = 0xFF000000 | (255 << 16) | (255 << 8) | 255;

					} else {
						pixels[pos] = 0xFF000000 | (178 << 16) | (34 << 8) | 52;
					}
				}
			}
		}
	}

	/* Erste Version
	 private void generateCzechFlag(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				// Wenn der aktuelle Pixel oberhalb der Mitte liegt
				if (y < height / 2) {
					// Pixel wird nur ausgefüllt, wenn die x-Position
					 // den y-Wert nicht übersteigt, damit wird eine
					 // Diagonale erzeugt.
					if (x < y) {

						pixels[pos] = 0xFF000000 | (0 << 16) | (0 << 8) | 255;
					} else {

						pixels[pos] = 0xFF000000 | (255 << 16) | (255 << 8) | 255;
					}
				} else {
					if (x < height - y) {

						pixels[pos] = 0xFF000000 | (0 << 16) | (0 << 8) | 255;
					} else {

						pixels[pos] = 0xFF000000 | (255 << 16) | (0 << 8) | 0;
					}
				}
			}
		}
	}*/

	// Farbcodes gemäß https://www.schemecolor.com/czech-republic-flag-colors.php
	private void generateCzechFlag(int width, int height, int[] pixels) {
		// Seitenverhältnis der Flagge berechnen.
		double ratio = (width * 1.0) / height;
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				// Obere hälfte der Flagge zeichnen
				if (y < height / 2) {
					// Wenn der X-Wert neidriger ist als der Y-Wert
					// setze Pixel, dabei wird die Diagonale erzeugt.
					if (x < y * ratio) {
						// Der obere linke Dreieck-Teil wird blau gefärbt.
						pixels[pos] = 0xFF000000 | (17 << 16) | (69 << 8) | 126;
					} else {
						// Ansonsten wird die obere Hälfte weiß gefärbt.
						pixels[pos] = 0xFF000000 | (255 << 16) | (255 << 8) | 255;
					}
				} else {
					if (x < width - y * ratio) {
						// Der untere linke Dreieck-Teil wird blau gefärbt.
						pixels[pos] = 0xFF000000 | (17 << 16) | (69 << 8) | 126;
					} else {
						// Ansonsten wird die untere Hälfte rot gefärbt.
						pixels[pos] = 0xFF000000 | (215 << 16) | (20 << 8) | 26;
					}
				}
			}
		}
	}
	//My Codes end here. Made by Nermin Rustic :)
	private void dialog() {
		// Dialog fuer Auswahl der Bilderzeugung
		GenericDialog gd = new GenericDialog("Bildart");

		gd.addChoice("Bildtyp", choices, choices[0]);

		gd.showDialog(); // generiere Eingabefenster

		choice = gd.getNextChoice(); // Auswahl uebernehmen

		if (gd.wasCanceled())
			System.exit(0);
	}
}
