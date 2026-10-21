# Data Structures in Java

Implementations of classic **abstract data types** in Java — binary trees, hash maps, binary search trees, binary heaps — together with small applications that use them and **empirical efficiency analysis**. Each folder is a standalone Eclipse/Java project with **JUnit 5** tests.

## Contents

| Project | Data structure / topic | Highlights |
|---|---|---|
| [`08-binary-tree`](08-binary-tree) | Binary tree with parent pointers | `ArbolBinarioPH` (nodes linked to parent and children) + recursive algorithms: count occurrences (preorder), find first node with equal children (inorder) |
| [`09-hash-map`](09-hash-map) | Hash map with separate chaining | `MapaDispersionAbierta` implementing a generic `IMapa<K,V>`; used by an orders/products management model |
| [`10-ordered-map-bst`](10-ordered-map-bst) | Ordered map on a binary search tree | `MapaABB`: insertion, lookup, deletion and in-order traversal |
| [`11-rental-company-app`](11-rental-company-app) | Applying JCF collections | Vehicle rental company: offices, vehicles and rentals, with GUI and tests |
| [`12-priority-queue-heap`](12-priority-queue-heap) | Priority queue on a binary heap | Bounded generic `ColaPrioMonticulo` (array-based heap) + job-candidate ranking application |
| [`13-ordered-sequence-bst`](13-ordered-sequence-bst) | Ordered sequence on a BST | `SecuenciaOrdenadaABB` used to manage cleaning teams and clients |
| [`14-algorithm-efficiency`](14-algorithm-efficiency) | Complexity in practice | Word-frequency ranking of *Don Quijote* (list O(n²) vs `HashMap` O(n)) checking **Zipf's law**, and common-elements algorithms with timing measurements |

## Running

Each project follows the Eclipse layout (`<project>/src/<package>`). Import a folder as a Java project in Eclipse/IntelliJ, add **JUnit 5** to the build path and run the `*Test.java` classes.

The GUIs and timing programs in projects 11, 12 and 14 use `fundamentos`, a small library of simple GUI dialogs (menus, input forms, messages) provided by the Universidad de Cantabria for this course, which must be added to the build path.

## Tech

Java · Generics · JUnit 5 · Java Collections Framework

*Coursework from the Data Structures course — Computer Engineering, Universidad de Cantabria.*
