# Ghost Net Fishing

Java-EE-Webanwendung zur Erfassung, Verwaltung und Bergung von Geisternetzen.  
Dieses Projekt wurde im Rahmen einer universitären Fallstudie entwickelt.

## Beschreibung

Ghost Net Fishing ist eine Webanwendung zur Unterstützung der Verwaltung verlorener Fischernetze (Geisternetze). Die Anwendung ermöglicht es, gefundene Geisternetze zu melden, deren Status zu verwalten und die Bergung durch Personen oder Organisationen zu koordinieren.

Die Anwendung stellt folgende Funktionen bereit:

- Erfassung neuer Geisternetze mit GPS-Koordinaten und geschätzter Größe
- Anzeige aller registrierten Geisternetze
- Filterung noch zu bergender Geisternetze
- Eintragung bergender Personen für ein Geisternetz
- Aktualisierung des Bergungsstatus
- Markierung eines Geisternetzes als geborgen oder verschollen
- Löschen bestehender Einträge

## Verwendete Technologien

Die Anwendung wurde mit folgenden Technologien umgesetzt:

- Java
- Jakarta EE
- Jakarta Server Faces (JSF)
- PrimeFaces
- Jakarta Persistence API (JPA)
- MySQL
- Maven
- Apache TomEE

## Architektur

Die Anwendung basiert auf einer mehrschichtigen Architektur:

- Präsentationsschicht: JSF-Seite mit PrimeFaces-Komponenten
- Controller-Schicht: Managed Bean zur Verarbeitung von Benutzerinteraktionen
- Service-Schicht: Umsetzung der Geschäftslogik und Datenbankoperationen
- Persistenzschicht: JPA-Entitäten zur Abbildung der Datenbankstruktur

## Hauptkomponenten

- `GhostNetController` – verarbeitet Benutzereingaben und steuert die Anwendung
- `GhostNetService` – enthält Geschäftslogik und verwaltet Datenbankzugriffe
- `GhostNet` – Entität zur Darstellung eines gemeldeten Geisternetzes
- `BergendePerson` – Entität zur Darstellung einer Person oder Organisation, die eine Bergung übernimmt
- `GhostNetStatus` – Enum zur Verwaltung des aktuellen Status eines Geisternetzes

## Datenbank

Die Anwendung verwendet eine relationale MySQL-Datenbank.

Die Datenbank enthält Tabellen zur Speicherung von:

- Geisternetzen
- bergenden Personen

Die Beziehung zwischen den Entitäten wird über JPA umgesetzt.

## Projektkontext

Dieses Repository dient der Dokumentation und Bereitstellung des Quellcodes einer Fallstudie im Rahmen des Studiums.
