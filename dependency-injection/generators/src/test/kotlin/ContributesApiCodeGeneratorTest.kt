import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.ExperimentalAnvilApi
import com.squareup.anvil.compiler.internal.testing.compileAnvil
import com.tschuchort.compiletesting.KotlinCompilation.ExitCode.OK
import dagger.Provides
import dagger.Reusable
import org.junit.Assert
import org.junit.Test
import java.lang.reflect.AnnotatedElement

@OptIn(ExperimentalAnvilApi::class)
class ContributesApiCodeGeneratorTest {

    @Test
    fun `a dagger module is generated`() {
        compileAnvil(
            """
                package com.test

                import com.adkhambek.di.ContributesAPI
                import com.adkhambek.di.UserScope

                @ContributesAPI(UserScope::class)
                interface TestApi
            """
        ) {
            Assert.assertEquals(exitCode, OK)

            val clazz = classLoader.loadClass("com.test.TestApi_Module")

            val contributesToAnnotation = clazz.annotation<ContributesTo>()
            Assert.assertEquals(contributesToAnnotation.scope, Any::class)

            val providerMethod = clazz.declaredMethods.single()

            Assert.assertEquals(providerMethod.returnType, classLoader.loadClass("com.test.TestApi"))
            Assert.assertTrue(
                providerMethod.annotations.map { it.annotationClass }.containsAll(
                    listOf(Provides::class, Reusable::class)
                )
            )
        }
    }
}

inline fun <reified T> AnnotatedElement.annotationOrNull(): T? =
    annotations.singleOrNull { it.annotationClass == T::class } as? T

inline fun <reified T> AnnotatedElement.annotation(): T =
    requireNotNull(annotationOrNull<T>()) { "Couldn't find annotation ${T::class}" }
