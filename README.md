# Custom HashMap Implementation in Java

This project is a custom implementation of a generic HashMap in Java built from scratch to understand how HashMaps work internally and why operations like `put()`, `get()`, and `remove()` work in O(1) average time complexity.

# How HashMap Achieves O(1) Average Complexity

HashMap uses:
- Hash Functions to convert keys into bucket indexes
- Buckets to store data efficiently
- Chaining using LinkedLists to handle collisions
- Rehashing to maintain a good load factor and avoid performance degradation

Instead of searching linearly through all elements, the hash function directly maps a key to a bucket index, allowing near constant-time access on average.

# Features
- Generic `<K, V>` implementation
- Custom hash function using `hashCode()`
- Collision handling using chaining
- Dynamic rehashing
- Load factor monitoring
- Core operations:
  - `put()`
  - `get()`
  - `remove()`
  - `containsKey()`
  - `keySet()`

# Concepts Learned
- Hashing
- Collision handling
- Buckets and chaining
- Load factor
- Rehashing
- Generic classes in Java
- Average vs Worst Case Time Complexity

# Tech Stack
- Java
- Collections Framework (LinkedList, ArrayList)

# Purpose
I built this project after repeatedly using HashMaps in DSA problems without fully understanding their internal implementation. This helped me understand how efficient key-value lookup works internally.
