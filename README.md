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
Für die Home-Automation möchte ich ein Monitoring und eine zentrale Steuerung für sowohl WLAN-fähige als auch analoge Geräte dann via GPIO ansteuern.
Gleichzeitig möchte ich sämtliche Geräte via analogen Schalter(gpio-Chips) steuern.(dezentral) Falls die zentrale Steuerung ausfällt, bleibt so die Steuerbarkeit und Status jederzeit der Status der Komponenten erhalten.

Dazu werden folgende Komponenten und Libraries vorgeschlagen:
1. 


Dieser Webserver enthält einen Webserver, der theoretisch via einem deployable Service gehostet werden und angesprochen werden kann.:
Aktuell erhält er nur eine Konvertierung von String auf JSON.
TODO:
GPIO to String Konvertierung, die den Rasperry-Pi scannt und in Events umwandelt.
Das https://www.home-assistant.io/integrations/remote_rpi_gpio/ gibt es schon, evtl. das erstmal anschauen, vielleicht ist das gut. Dann könnte man das weglassen.

