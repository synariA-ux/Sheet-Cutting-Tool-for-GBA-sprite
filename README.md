# Sprite Sheet Cutting Tool for GBA Sprites

A simple Java GUI tool that splits Pokémon Game Boy Advance sprite sheets into individual sprite files and palette files.

This tool supports sprite sheets used for **Moemon Emerald** and **Moemon FireRed**.

---

## Features

* Split **Emerald sprite sheets**
* Split **FireRed sprite sheets**
* Export:

  * Front sprites
  * Back sprites
  * Animated sprites
  * Shiny variants
* Automatically generates `.pal` palette files
* Simple graphical interface

---

## Download

Download the latest compiled tool from the **Releases** page.

```
SpriteSheetCuttingTool.jar
```

Run the tool by double-clicking the `.jar` file or using:

```
java -jar SpriteSheetCuttingTool.jar
```

---

## Folder Structure

Before running the tool, create these folders in the same directory as the `.jar`.

### Emerald sprites

Input folder:

```
inputEM
```

Output folder:

```
outputEM
```

File format expected:

```
PokemonName.png
```

Example:

```
Pikachu.png
Charizard.png
```

---

### FireRed sprites

Input folder:

```
inputFR
```

Output folder:

```
outputFR
```

File format expected:

```
PokemonID.png
```

Example:

```
001.png
025.png
150.png
```

---

## How to Use

1. Launch the program
2. Place sprite sheets in the correct input folder
3. Select the sprite categories you want to export
4. Click:

```
Split Emerald Sprites
```

or

```
Split FireRed Sprites
```

The processed sprites will appear in the output folders.

---

## Requirements

* Java **17 or newer**

You can check your Java version with:

```
java -version
```

---

## Source Code

The Java source files are located in the `src` folder.

---

## License

This project is released under the **MIT License**.
