# Changelog / Features

## Architektur
- Clean Architecture Struktur
- Dependency Injection mit Hilt
- Repository Pattern
- MVVM Pattern mit Jetpack Compose

## Datenfluss
- Kotlin Flow Integration
- StateFlow für UI-Updates
- Coroutines für asynchrone Operationen

## Datenmodell
- `Workout` Domain-Modell mit Feldern für id, name, description, duration, caloriesBurned, type, category, date
- `WorkoutType` Enum (CARDIO, STRENGTH, FLEXIBILITY, HIIT)
- `WorkoutEntity` für Room-Datenbank

## Datenbank
- Room-Integration mit WorkoutDao
- CRUD-Operationen für Workouts
- Kategorie-basierte Abfragen

## UI-Komponenten
- `EditWorkoutDialog` zum Erstellen/Bearbeiten von Workouts
  - Textfelder für alle Workout-Eigenschaften
  - Dropdown für WorkoutType
  - Validierung der Eingaben

## Screens
- `CardioWorkoutScreen`
  - Liste aller Cardio-Workouts
  - Suchfunktion
  - Leerer Zustand
- `WorkoutScreen`
  - Detailansicht eines Workouts
  - Bearbeiten/Löschen Funktionalität

## Repository
- `WorkoutRepository` Interface
- `WorkoutRepositoryImpl` Implementation
- Mapping zwischen Entity und Domain-Modellen

## ViewModels
- `StatisticsViewModel`
  - Gesamtanzahl der Workouts
  - Durchschnittliche Workout-Dauer
  - Gesamte verbrannte Kalorien
  - Vollständige Workout-Listen pro Kategorie für detaillierte Statistiken

## Navigation
- Bottom Navigation
- Screen-zu-Screen Navigation
- Zurück-Navigation

## Mapper
- `WorkoutMapper` für Konvertierung zwischen Entity und Domain-Modellen

## Letzte Änderungen
- Verbessertes Dropdown-Menü für WorkoutType
- Explizite Typisierung in EditWorkoutDialog
- Fehlerbehandlung für leere Workouts
- StatisticsViewModel optimiert für detaillierte Kategorie-Auswertungen
- WorkoutMapper für Entity/Domain Konvertierung implementiert
- EditWorkoutDialog mit ViewModel Integration

## Fehlerbehandlung
- Validierung von Benutzereingaben
- Null-Safety
- Fehlerbehandlung bei Datenbankoperationen

## Testing
- Unit Tests für Repository
- Unit Tests für ViewModels
- UI Tests für Compose Komponenten

## Performance
- Lazy Loading in Listen
- Effizientes State Management
- Optimierte Datenbankabfragen

## Geplante Features
- Workout-Statistiken Visualisierung
- Workout-Pläne
- Benachrichtigungen für geplante Workouts
- Export/Import von Workout-Daten

## Bekannte Probleme
- [Liste von bekannten Bugs oder Einschränkungen]

## Versionshistorie
v1.0.1
- Implementierung des WorkoutMappers
- Verbesserung des EditWorkoutDialogs
- Hinzufügung der Statistik-Funktionen

## [1.0.2] - 2024-12-15

### Fixed
- UI: Added bottom padding (80.dp) to HomeScreen to prevent content from being hidden behind bottom navigation bar
- Database: Updated Room database version from 1 to 3 to accommodate schema changes
- Database: Added fallbackToDestructiveMigration to handle database schema updates

### Changed
- Database: Optimized WorkoutDao queries for better performance
- Database: Improved WorkoutDao implementation for category-based queries
- UI: Redesigned HomeScreen layout with four featured workout cards (Strength, Cardio, HIIT, Flexibility)
- UI: Moved "Statistiken" and "Training starten" buttons below featured cards
- UI: Improved spacing and layout consistency in HomeScreen

### Added
- Database: Added new schema version (version 3) for Room database
- UI: Added NEW label to featured workout cards
- UI: Added gradient overlay to featured cards for better text readability