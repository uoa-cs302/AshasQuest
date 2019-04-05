# 2019-Java-Group19
Unicorn Dragon Studios
Java game repository for M.Hassaan Mirza and Kimberley Evans-Parker

`AshasQuest` Starting point of the code. Initializes window. 
`Window` Chooses map that will be displayed. Reads map and creates tiles based of that using `Tile`. Draws components. 
`Tile` Based on the room's terrain and type of tile needed, will grab an image from `Sprite` using the spritesheet name, tile_size and row and col coordinates. 
`Sprite` Loads a subimage of the sprite or tile at the given location. 

maps contains the different map layouts as txt files
sprites contains the different spritesheets as png files

Map design
0 <- 1 -> 2 -> 3 -> 4
	  |
	  V
	  5 -> 6 -> 7
	  |
	  V
	  8 -> 9 -> 10 -> 11
