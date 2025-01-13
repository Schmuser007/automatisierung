# Automatisierung

Benötigt:
Java17
https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

Maven.

Bauen: mvn install
Starten: mvnw spring-boot:run
Frameworks:
SpringBoot Starter

Gesamtprojektbeschreibung:
Def.: Aktor:

In der Home Automation bezeichnet ein Aktor ein Gerät, das physische Aktionen ausführt oder steuert, basierend auf Steuerbefehlen eines Systems. Aktoren setzen elektronische Signale in mechanische Bewegungen, Schaltvorgänge oder andere physikalische Aktionen um. Sie bilden das Gegenstück zu Sensoren, die Daten erfassen und an das System weiterleiten.

Für die Home-Automation möchte ich ein Monitoring und eine zentrale Steuerung für Aktoren, wie z.B. binäre Aktoren?
1. Beispielhaftes Szenario:
   1.1 Lampe-Ein-Aus: Lampe ist IP-fähig. System schickt bei Knopfdruck Befehl über TCP an Lampe.     Lampe kann Signal direkt verwerten.
   Schematisch: Digitales Signal wird digital(TCP) an digitalen Aktor gesendet.
   Nachteil: großer Switch.
2. Beispielhaftes Szenario:


Die 
Worauf wird das System betrieben? Raspi?Dediz.Maschine? 

Gleichzeitig möchte ich sämtliche Geräte via analogen Schalter(gpio-Chips) steuern.(dezentral) Falls die zentrale Steuerung ausfällt, bleibt so die Steuerbarkeit und Status jederzeit der Status der Komponenten erhalten.
Rollos, alle Lampen

Dazu werden folgende Komponenten und Libraries vorgeschlagen:
1. Zentrales Userinterface, Dashboards, Steuerung: Home Automation?


Dieser Webserver enthält einen Webserver, der theoretisch via einem deployable Service gehostet werden und angesprochen werden kann.:
Aktuell erhält er nur eine Konvertierung von String auf JSON.
TODO:
GPIO to String Konvertierung, die den Rasperry-Pi scannt und in Events umwandelt.
Das https://www.home-assistant.io/integrations/remote_rpi_gpio/ gibt es schon, evtl. das erstmal anschauen, vielleicht ist das gut. Dann könnte man das weglassen.

RS232

