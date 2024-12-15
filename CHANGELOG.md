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
  - Workouts pro Kategorie

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