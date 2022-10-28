package uebung1;

import ij.ImageJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

//erste Uebung (elementare Bilderzeugung)

public class FlagsAndPatters implements PlugIn {
	
	final static String[] choices = {
		// Original Aufgaben
		"Schwarzes Bild",
		"Gelbes Bild", 
		"Belgische Fahne",
		"Niederländische Fahne",
		"Italienische Fahne",
		"Horiz. Schwarz/Weiss Verlauf",
		"Horiz. Gelb /Türkis",
		"Horiz. Schwarz/Rot vert. Schwarz/Blau Verlauf",
		"USA Fahne",
		"Tschechische Fahne",
		"Japanische Fahne",
		"--- Challenges ---",
		"Sonnenuntergang Verlauf",
		"Diagonal Verlauf Muster",
		"Rahmen Muster",
		"Schachbrett Muster",
		"Kongolesische Fahne",
		"Fahne der Seychellen",
		"Brazilianische Fahne",
		"Guyanische Fahne"
	};
	
	private String choice;
	
	public static void main(String args[]) {
		ImageJ ij = new ImageJ(); // neue ImageJ Instanz starten und anzeigen 
		ij.exitWhenQuitting(true);
		
		FlagsAndPatters imageGeneration = new FlagsAndPatters();
		imageGeneration.run("");
	}
	
	public void run(String arg) {
		
		int width  = 566;  // Breite
		int height = 400;  // Hoehe
		
		// RGB-Bild erzeugen
		ImagePlus imagePlus = NewImage.createRGBImage("GLDM_U1", width, height, 1, NewImage.FILL_BLACK);
		ImageProcessor ip = imagePlus.getProcessor();
		
		// Arrays fuer den Zugriff auf die Pixelwerte
		int[] pixels = (int[])ip.getPixels();
		
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
		
		if ( choice.equals("Niederländische Fahne") ) {
			generateNetherlandsFlag(width, height, pixels);
		}
		
		if ( choice.equals("Italienische Fahne") ) {
			generateItalianFlag(width, height, pixels);
		}
		
		if (choice.equals("Horiz. Schwarz/Weiss Verlauf")) {
			generateBWGradient(width, height, pixels);
		}
		
		if ( choice.equals("Horiz. Gelb /Türkis") ) {
			generateYellowTurquoiseGradient(width, height, pixels);
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
		
		if ( choice.equals("Japanische Fahne") ) {
			generateJapaneseFlag(width, height, pixels);
		}
		// Challenges, New Patters
		if ( choice.equals("Sonnenuntergang Verlauf") ) {
			generateSunsetGradient(width, height, pixels);
		}
		
		if ( choice.equals("Diagonal Verlauf Muster") ) {
			generateCrosspatternGradient(width, height, pixels);
		}
		
		if ( choice.equals("Rahmen Muster") ) {
			generateNestedBoderPattern(width, height, pixels);
		}
		
		if ( choice.equals("Schachbrett Muster") ) {
			generateCheckboardPattern(width, height, pixels);
		}
		// New Flags
		if ( choice.equals("Kongolesische Fahne") ) {
			generateCongoleseFlag(width, height, pixels);
		}
		
		if ( choice.equals("Fahne der Seychellen") ) {
			generateSeychellesFlag(width, height, pixels);
		}
		
		if ( choice.equals("Brazilianische Fahne") ) {
			generateBrazilianFlag(width, height, pixels);
		}
		
		if ( choice.equals("Guyanische Fahne") ) {
			generateGuyaneseFlag(width, height, pixels);
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
				
				// Werte zurueckschreiben, wir können direkt aboslut arbeiten, Farben ändern sich nie
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
				
				if (x < 1.0 * width / 3.0) {
					/* Wenn der Pixel im ersten vertikalen Drittel ist
					 * (also links), dann schwarz einfärben.*/
					pixels[pos] = 0xFF000000 | (0 << 16) | (0 << 8) | 0;
				
				} else if (x > (1.0 * width / 3.0) * 2.0) {
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
	
	//Farbcodes gemäß https://www.schemecolor.com/netherlands-flag-colors.php
	private void generateNetherlandsFlag(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				
				// Wie belgische Fahne, nur eben vertikal
				if (y < 1.0 * height / 3.0) {
					/* Wenn der Pixel im ersten horizontalen Drittel ist
					 * (also oben), dann rot einfärben.*/
					// Für Italien RGB (0, 140, 69)
					pixels[pos] = 0xFF000000 | (174 << 16) | (70 << 8) | 40;
				
				} else if (y > (height * 2.0) / 3.0) {
					/* Wenn der Pixel im letzten horizontalen Drittel ist
					 * (also unten), dann blau einfärben.*/
					// Für Italien RGB (205, 33, 42)
					pixels[pos] = 0xFF000000 | (33 << 16) | (70 << 8) | 139;

				} else {
					// Ansonsten den Rest (Mitte) weiß färben.
					// Für Italien RGB (244, 245, 240)
					pixels[pos] = 0xFF000000 | (255 << 16) | (255 << 8) | 255;

				}
			}
		}
	}
	
	//Farbcodes gemäß https://www.schemecolor.com/italy-flag-colors.php
	private void generateItalianFlag(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				
				// Wie niederländische Fahne, nur andere Farben
				if (y < 1.0 * height / 3.0) {
					/* Wenn der Pixel im ersten horizontalen Drittel ist
					 * (also oben), dann grün einfärben.*/
					pixels[pos] = 0xFF000000 | (0 << 16) | (140 << 8) | 69;
				
				} else if (y > (height * 2.0) / 3.0) {
					/* Wenn der Pixel im letzten horizontalen Drittel ist
					 * (also unten), dann rot einfärben.*/
					pixels[pos] = 0xFF000000 | (205 << 16) | (33 << 8) | 42;

				} else {
					// Ansonsten den Rest (Mitte) weiß färben.
					pixels[pos] = 0xFF000000 | (244 << 16) | (245 << 8) | 240;

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
	
	// It quasi das selbe wie Schwarz/Weiß aber mit gegenläufigen und getrennten farben.
	private void generateYellowTurquoiseGradient(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				
				/* Die x Werte mit 1.0 multiplizieren,
				 * um Double-Werte zu erzeugen, für eine präzise
				 * Berechnung der Mischfarbwerte.
				 * Horizontalwerte in Verhältnis zur Breite setzen.*/
				
				int r = (int) (255 - 255 * (1.0 * x / width));
				// Weil rot im Verlauf weniger wird, muss es vom maximal wert anteilig abgezogen werden
				int g = 255; // Grün ist in Gelb und Türkis immer voll vorhanden
				// Blau wird im verlauf mehr, wie X. Der Anteil wird einfach mit dem maximalen Wert multipliziert
				int b = (int) (255 * (1.0 * x / width));
				
				// Die Double werte in Integer casten und darstellen.
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
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
				pixels[pos] = 0xFF000000 | (red << 16) | (0 << 8) | blue;
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
					 * um eine Typenumwandlung vorzunehmen, für präzise Rechnung.*/
					pixels[pos] = 0xFF000000 | (60 << 16) | (59 << 8) | 110;

				} else {
					if (1.0 * y % (2*stripes) >= stripes) {
						
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
		// Mitte berechnen, damit man das nicht immer wieder macht
		double yMid = height / 2.0;
		
		// Seitenverhältnis der Flagge berechnen.
		double ratio = (1.0 * width) / height;
		
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				
				// Obere hälfte der Flagge zeichnen
				if (y < yMid) {
					// Wenn der X-Wert neidriger ist als der Y-Wert
					// setze Pixel, dabei wird die Diagonale erzeugt.
					if (x < y * ratio) {
						// Der obere linke Dreieck-Teil wird blau gefärbt.
						pixels[pos] = 0xFF000000 | (17 << 16) | (69 << 8) | 126;
					} else {
						// Ansonsten wird die obere Hälfte weiß gefärbt.
						pixels[pos] = 0xFF000000 | (255 << 16) | (255 << 8) | 255;
					}
				// Untere Hälfte
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
	
	// Farbcodes gemäß https://www.schemecolor.com/japan-flag-colors.php
	private void generateJapaneseFlag(int width, int height, int[] pixels) {
		// Radius des Kreises einmalig festlegen, da es sich nicht ändert, Durchmesser ist 3/5 der Höhe
		double radius = (height * 3.0 / 5.0) / 2.0;
		
		// Mittelpunkte berechnen, auch nur einmalig nötig, daher außerhalb der Loops
		double xMid = width / 2.0;
		double yMid = height / 2.0;
		
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				
				// Koordinatengleichung, euklidischer Abstand nutzen
				double p = Math.sqrt(Math.pow((x - xMid), 2) + Math.pow((y - yMid), 2));
				
				// Punkt liegt in der Kreisfläche, ist dann rot
				if (p < radius) {
					pixels[pos] = 0xFF000000 | (188 << 16) | (0 << 8) | 45;
				} else {
					pixels[pos] = 0xFF000000 | (255 << 16) | (255 << 8) | 255;
				}
			}
		}
	}
	//My Codes end here. Made by n-c0de-r :)
	
	// Farbverlauf Kreisausschnitt Sonne Gelb/Orange, Himmel: Blau/Violett (oben nach unten)
	private void generateSunsetGradient(int width, int height, int[] pixels) {
		// Radius ist halbe Breite des Bildes, dann passt es rein
		double radius = width / 2.0;
		
		// Mittelpunkte berechnen, auch nur einmalig nötig, daher außerhalb der Loops
		double xMid = width / 2.0;
		double yMid = height; // Mittelpunkt liegt unten
		
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				
				// Gleichung wie bei Japanflagge nutzen
				double p = Math.sqrt(Math.pow((x - xMid), 2) + Math.pow((y - yMid), 2));
				
				int r, g, b;
				
				if (p < radius) {
					g = (int) (255.0 - (128.0 * y / height)); // Grün wird um die Hälfte reduziert
					// Rot und Blau bleiben fix
					pixels[pos] = 0xFF000000 | (255 << 16) | (g << 8) | 0;

				} else {
					r = (int) (128.0 * y / height);
					b = (int) (255.0 - 128.0 * y / height);
					pixels[pos] = 0xFF000000 | (r << 16) | (0 << 8) | b;
				}
			}
		}
	}
		
	// 4 Dreiecke mit verlauf, Oben: blau nach Unten:gelb; Links:grün nach Rechts: rot
	private void generateCrosspatternGradient(int width, int height, int[] pixels) {
		// Seitenverhältnis der Flagge berechnen.
		double ratio = (1.0 * width) / height;
		
		// Mittelpunkte berechnen, wenn die Farben wechseln
		double xMid = width / 2.0;
		double yMid = height / 2.0;
		
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				
				int r, g, b;
				
				if ((y < yMid && x > y * ratio && x < width - y * ratio) || // Oben
						(y > yMid && x < y * ratio && x > width - y * ratio)) { // Unten
					// Rot und Grün (Gelb) werden mehr
					r = (int) (255.0 * y / height);
					g = (int) (255.0 * y / height);
					// Blau wird im Verlauf weniger
					b = (int) (255.0 - 255.0 * y / height);
					pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
				}
				
				if ((x < xMid && y * ratio > x && y * ratio < width - x) || // Links
						(x > xMid && y * ratio < x && y * ratio > width - x)) { //Rechts
					// Rot wird im Verlauf weniger
					r = (int) (255.0 * x / width);
					// Grün wird im Verlauf weniger
					g = (int) (255.0 - 255.0 * x / width);
					
					b = 0;
					pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
				}
			}
		}
	}

	// Zeichne ein Bild aus 3 gleich dicken "Rahmen": innen blau, drum herum grün, außen rot
	private void generateNestedBoderPattern(int width, int height, int[] pixels) {
		//Seitenlängen eines "Quadrats" festlegen.
		double xWid = width / 5.0;
		double yWid = height / 5.0;
		
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				
				if (x<xWid || x > width-xWid || y<yWid || y > height-yWid) {
					pixels[pos] = 0xFF000000 | (255 << 16) | (0 << 8) | 0;
				}
				if ((x>=xWid && x<=width-xWid) && (y>=yWid && y<=height-yWid)){
					pixels[pos] = 0xFF000000 | (0 << 16) | (255 << 8) | 0;
				} 
				if ((x>=xWid*2 && x<=width-xWid*2) && (y>=yWid*2 && y<=height-yWid*2)) {
					pixels[pos] = 0xFF000000 | (0 << 16) | (0 << 8) | 255;
				}
			}
		}
	}
	
	// Zeichne ein Schachbrett mit der korrekten Anzahl an Feldern, im richtigen Verhältnis.
	private void generateCheckboardPattern(int width, int height, int[] pixels) {
		//Seitenlängen eines "Quadrats" festlegen.
		double xLen = width / 8.0;
		double yLen = height / 8.0;
		
		// Schleife ueber die y-Werte
		for (int y = 0; y < height; y++) {
			// Schleife ueber die x-Werte
			for (int x = 0; x < width; x++) {
				int pos = y * width + x; // Arrayposition bestimmen
				
				//Farbwerte initialisieren, blau kommt nicht vor!
				int r = 0;
				int g = 0;
				int b = 0;
				
				//Gerade Zeilen
				if (y % (yLen*2) >= yLen) {
					//Ungerade Spalten
					if (x % (xLen*2) > xLen) {
						r = 255;
						g = 255;
						b = 255;
					}
				} else { //Ungerade Zeilen
					//Gerade Spalten
					if (x % (xLen*2) < xLen) {
						r = 255;
						g = 255;
						b = 255;
					}
				}
				//Werte zurueckschreiben. Da blau nicht vorhanden, auf 0 setzen!
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
			}
		}
	}
	
	// Farbcodes gemäß https://www.schemecolor.com/congo-flag-colors.php
		private void generateCongoleseFlag(int width, int height, int[] pixels) {

			// Schleife ueber die y-Werte
			for (int y = 0; y < height; y++) {
				// Schleife ueber die x-Werte
				for (int x = 0; x < width; x++) {
					int pos = y * width + x; // Arrayposition bestimmen
					
					if (x < height-y) {
						pixels[pos] = 0xFF000000 | (0 << 16) | (149 << 8) | 67; // Grün
					} else if (x > width-y) {
						pixels[pos] = 0xFF000000 | (220 << 16) | (36 << 8) | 31; // Rot
					} else {
						pixels[pos] = 0xFF000000 | (251 << 16) | (222 << 8) | 74; // Gelb
					} 
				}
			}
		}
		
		// Farbcodes gemäß https://www.schemecolor.com/seychelles-flag-colors.php
		private void generateSeychellesFlag(int width, int height, int[] pixels) {
			
			// Schleife ueber die y-Werte
			for (int y = 0; y < height; y++) {
				// Schleife ueber die x-Werte
				for (int x = 0; x < width; x++) {
					int pos = y * width + x; // Arrayposition bestimmen
					if (x < width/3.0 - width/3.0* (1.0*y/height)) {
						pixels[pos] = 0xFF000000 | (0 << 16) | (63 << 8) | 135; // Blau
					} else if (x < width*2.0/3.0 - width*2.0/3.0 * (1.0*y/height)) {
						pixels[pos] = 0xFF000000 | (252 << 16) | (216 << 8) | 86; // Gelb
					} else if (x < width*4.0/3.0 - width*4.0/3.0 * (1.0*y/height)) {
						pixels[pos] = 0xFF000000 | (214 << 16) | (40 << 8) | 40; // Rot
					} else if (x < width*8.0/3.0 - width*8.0/3.0 * (1.0*y/height)) {
						pixels[pos] = 0xFF000000 | (255 << 16) | (255 << 8) | 255; // Grün
					} else {
						pixels[pos] = 0xFF000000 | (0 << 16) | (122 << 8) | 61; // Grün
					}
				}
			}
		}

		// Farbcodes gemäß https://www.schemecolor.com/brazil-flag-colors.php
		private void generateBrazilianFlag(int width, int height, int[] pixels) {
			// Radius ist 1/4 der Höhe
			double radius = (height / 4.0);
			// Abstände des Rhombus: (radius-1/10 des radius) / 2
			double border = (radius - (radius / radius)*10.0) / 2;
			
			// Mittelpunkte berechnen
			double xMid = width / 2.0;
			double yMid = height / 2.0;
			
			// Seitenverhältnis der Flagge berechnen.
			double ratio = (1.0 * width) / height;
			
			// Schleife ueber die y-Werte
			for (int y = 0; y < height; y++) {
				// Schleife ueber die x-Werte
				for (int x = 0; x < width; x++) {
					int pos = y * width + x; // Arrayposition bestimmen
					
					// Wie bei Japanischer Flagge
					double p = Math.sqrt(Math.pow((x - xMid), 2) + Math.pow((y - yMid), 2));
					
					// Punkt liegt in der Kreisfläche, ist dann rot
					if (p < radius) {
						pixels[pos] = 0xFF000000 | (0 << 16) | (39 << 8) | 118; // Blau
					} else if (x < xMid-border+y*ratio && x < xMid-border+(width-y*ratio) && x > xMid+border-y*ratio && x > xMid+border-(width-y*ratio)){
						pixels[pos] = 0xFF000000 | (255 << 16) | (223 << 8) | 0; // Gelb
					} else {
						pixels[pos] = 0xFF000000 | (0 << 16) | (156 << 8) | 56; // Grün
					}
				}
			}
		}
			
		// Farbcodes gemäß https://www.schemecolor.com/guyana-flag-colors.php
		private void generateGuyaneseFlag(int width, int height, int[] pixels) {
			// Seitenverhältnis der Flagge berechnen.
			double ratio = (1.0 * width) / height;
			
			// Streifen Relation bestimmen
			double xStripe = width / 60.0;
			double yStripe = height / 30.0;
			
			// Schleife ueber die y-Werte
			for (int y = 0; y < height; y++) {
				// Schleife ueber die x-Werte
				for (int x = 0; x < width; x++) {
					int pos = y * width + x; // Arrayposition bestimmen
					
					// Inneres Dreieck
					if ((x+xStripe < y * ratio) && (x+xStripe < width-y*ratio)) {
						pixels[pos] = 0xFF000000 | (206 << 16) | (17 << 8) | 38; // Rot	
					} else if ((x < y * ratio) && (x < width-y*ratio)) {
						pixels[pos] = 0xFF000000 | (0 << 16) | (0 << 8) | 0; // Schwarz
					} else {
						// Äußeres Dreieck
						if ((x+yStripe < y * ratio*2) && (width - x-yStripe > (y * 2 - height)*ratio)) {
							pixels[pos] = 0xFF000000 | (252 << 16) | (209 << 8) | 22; // Gelb	
						} else {
							pixels[pos] = 0xFF000000 | (255 << 16) | (255 << 8) | 255; // Weiß
						}
						// Feld
						if((x > y * ratio*2) || (width - x < (y * 2 - height)*ratio)) {
							pixels[pos] = 0xFF000000 | (0 << 16) | (158 << 8) | 73; // Grün
						}
					}
					
				}
			}
		}
	
	private void dialog() {
		// Dialog fuer Auswahl der Bilderzeugung
		GenericDialog gd = new GenericDialog("Bildart");
		
		gd.addChoice("Bildtyp", choices, choices[0]);
		
		
		gd.showDialog();	// generiere Eingabefenster
		
		choice = gd.getNextChoice(); // Auswahl uebernehmen
		
		if (gd.wasCanceled())
			System.exit(0);
	}
}
