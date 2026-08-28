package com.example.model

object PackageNameValidator {

    data class ValidationResult(
        val isValid: Boolean,
        val errorMessage: String? = null
    )

    fun validate(packageName: String): ValidationResult {
        val trimmed = packageName.trim()
        
        if (trimmed.isEmpty()) {
            return ValidationResult(
                isValid = false,
                errorMessage = "Package name cannot be empty. Example: com.example.myapp"
            )
        }

        if (trimmed.any { it.isUpperCase() }) {
            return ValidationResult(
                isValid = false,
                errorMessage = "Package names must be all lowercase letters (no uppercase allowed)."
            )
        }

        if (trimmed.contains(" ")) {
            return ValidationResult(
                isValid = false,
                errorMessage = "Package names cannot contain spaces. Use dots to separate words (e.g., com.example.myapp)."
            )
        }

        if (trimmed.contains("-")) {
            return ValidationResult(
                isValid = false,
                errorMessage = "Hyphens (-) are not allowed in package names. Use letters, numbers, or dots."
            )
        }

        if (!trimmed.contains(".")) {
            return ValidationResult(
                isValid = false,
                errorMessage = "Package name must contain at least one dot separating parts (e.g. com.mycompany.app)."
            )
        }

        val segments = trimmed.split(".")
        if (segments.size < 2) {
            return ValidationResult(
                isValid = false,
                errorMessage = "A valid package name needs at least two segments (e.g., com.myapp or com.creator.myapp)."
            )
        }

        for ((index, segment) in segments.withIndex()) {
            if (segment.isEmpty()) {
                return ValidationResult(
                    isValid = false,
                    errorMessage = "Package name has an empty segment (consecutive dots or leading/trailing dots)."
                )
            }

            if (!segment.first().isLetter()) {
                return ValidationResult(
                    isValid = false,
                    errorMessage = "Part '${segment}' starts with a number or symbol. Each section must start with a letter (a-z)."
                )
            }

            if (!segment.all { it.isLetterOrDigit() || it == '_' }) {
                return ValidationResult(
                    isValid = false,
                    errorMessage = "Part '${segment}' contains special characters. Only lowercase letters (a-z) and numbers (0-9) are allowed."
                )
            }
        }

        return ValidationResult(isValid = true, errorMessage = null)
    }
}
