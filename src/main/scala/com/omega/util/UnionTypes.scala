package com.omega.util

object UnionTypes {
    type ¬[A] = A => Nothing
    type ¬¬[A] = ¬[¬[A]]
    type ∨[T, U] = ¬[¬[T] with ¬[U]]
    type |∨|[T, U] = { type λ[X] = ¬¬[X] <:< (T ∨ U) }
}
