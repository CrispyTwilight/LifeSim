<!-- Auth: John O'Neal
Date: 05/07/2024 -->

# Mobile Game Design Documentation: Life Sim

### Introduction
Welcome to the documentation for our mobile app game, a life simulation text-based adventure! In this document, you'll find an overview of the game's features, requirements, and how they've been implemented.

### Game Overview
Our game is a text-based life simulation where players navigate various life decisions through a series of choices presented in a card format. Players will progress through different scenarios, making choices that impact their virtual character's life outcomes.

### Basic Requirements Implementation
1. **Choice Implementation**:
    - We have implemented 20 choice cards for the users presented in a card format. Each choice card offers the player 3 different options to progress through the game.
    - John wrote 10 choice cards for the beginning of life and Hunter wrote 10 cards for the end of life.

2. **Data Persistence**:
    - Data from the user's choices is carried over to other activities, ensuring continuity in the player's journey. This is achieved through the utilization of Android's Room Database.

3. **Results Page**:
    - Upon completing a series of choices, players are directed to a results page where they can view the outcomes of their decisions, providing feedback on their progress and the consequences of their choices.

4. **Welcome Page**:
    - The game starts with a welcome page, introducing players to the concept of the game and setting the stage for their virtual journey.

### Custom Classes Implementation
To enhance the game's functionality and structure, we've implemented custom classes:
- **Character Class**: Manages the attributes and progress of the player's virtual character.
- **Choice Class**: Represents each choice presented to the player, containing relevant information and outcomes.

### Data Persistence with Room Database
To ensure a good experience and persistent storage of user progress, we've integrated Android's Room Database:
- **Save/Load Method**: Allows players to save their progress and resume their game at a later time.
- **Scoreboard System**: Tracks and displays user achievements and progress throughout the game.

## Tooling

### IDE:
```
Android Studio Jellyfish | 2023.3.1
Build #AI-233.14808.21.2331.11709847, built on April 12, 2024
Runtime version: 17.0.10+0--11572160 amd64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
Windows 11.0
GC: G1 Young Generation, G1 Old Generation
Memory: 2048M
Cores: 24
Registry: ide.experimental.ui=true
```

### Emulator:
```
Pixel 8 Pro API 34
Android 14.0 ("UpsideDownCake") I x86_64
```

### SDK Tools:
```
Android SDK Build-Tools 35-rc3
Android Emulator 34.2.13
Android Emulator hypervisor driver (installer) 2.0
Android SDK Platform-Tools 35.0.1
Intel x86 Emulator Accelerator (HAXM installer) - Deprecated 7.6.5
```

### SDK Platforms:
| Name                             | API Level | Revision |
|----------------------------------|-----------|----------|
| Android 14.0 ("UpsideDownCake")  | 34        | 3        |

## Project Settings
### Project Options
- Name - LifeSimApp
- Minimum SDK - API 28 ("Pie"; Android 9.0)
- Build configuration language - Kotlin DSL (build.gradle.kts)


### build.graddle Options
- minSdk = 28
- targetSdk = 34
- JavaVersion.VERSION_1_8
- gradle-8.6