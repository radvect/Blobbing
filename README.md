Description:
Blobs of various sizes are situated in a room. Each blob will move toward the nearest
smaller blob until it reaches it and engulfs it. After consumption, the larger blob grows in
size.
Your task is to create a class Blobservation (a portmanteau of blob and observation)
and methods that give information about each blob after an arbitrary number of moves.

Class Details
A Blobservation class instance is instantiated with two integer values, h and w , that
represent the dimensions of the room. The instance methods are as follows:
                          populate
The populate method is called with an array/list representing a list of blobs.
Each element is an object/dict ( Map<String,Integer> in Java) with the following
properties:
x : vertical position
y : horizontal position
size : size of the blob
This method may be called multiple times on the same class instance
If a new blob's position is already occupied by an existing blob, the two fuse into a
single blob
If the list input contains any invalid values, discard it entirely and throw an error
(do not update/modify the instance)
                            move
The move method may be called with up to one argument — a positive integer
representing the number of move iterations (ie. turns) to process. If no argument is given,
the integer value defaults to 1 .
                          print_state
The print_state method is a nullary function that returns an array of the positions and
sizes of each blob at the current state (Java: a List<List<Integer>> ), sorted in
ascending order by x position, then by y . If there are no blobs, return an empty array.


Blob Movement Behavior
With each turn, every blob whose size is larger than the smallest blob size value will
move to one of the 8 spaces immediately surrounding it (Moore neighborhood) in the
direction of the nearest target blob with a lower relative size .
If a target's coordinates differ on both axes, the predatory blob will move diagonally.
Otherwise, it will move in the cardinal direction of its target
If multiple targets are at the same movement distance, the blob with the largest
size is focused
If there are multiple targets that have both the largest size and shortest
movement distance, priority is set in clockwise rotation, starting from the 12
position
If two blobs pass each other (e.g. swap positions as a result of their movement), they
will not fuse
Blobs of the smallest size remain stationary
