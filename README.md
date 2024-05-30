# sword_health_cats

# Cat API Android App

## Overview

This Android app utilizes the Cat API (https://thecatapi.com/) to showcase images and information about cats. It is built using Kotlin and follows the MVVM architecture pattern in conjunction with Clean Architecture principles.

## Key Strategies

### MVVM Architecture

The project follows the MVVM (Model-View-ViewModel) architectural pattern to separate concerns and promote testability and maintainability.

### Clean Architecture

The codebase is organized into distinct layers - presentation, domain, and data - following Clean Architecture principles. This separation ensures a clear division of responsibilities and promotes code maintainability.

### Asynchronous Programming with RxKotlin

RxKotlin is used for handling asynchronous operations, such as network requests and data processing, providing a concise and reactive approach to programming.

### Room Database Integration

Room is used for local data persistence, allowing efficient storage and retrieval of cat data on the device. This integration required careful consideration of database design and implementation.

### Dependency Injection with Dagger

Dagger2 is employed for dependency injection, facilitating modularization and inversion of control. This promotes code decoupling and testability.

### Unit Testing with Mockk and JUnit

Unit tests are written using Mockk and JUnit to ensure code quality and robustness. Test-driven development principles are followed to validate individual components in isolation.
