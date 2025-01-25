package wa.umiushi.spring.boot.lab.cache

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Configuration(proxyBeanMethods = false)
@EnableCaching
class CacheConfig

@Service
class CacheExampleUseCase(
    private val repository: CacheExampleRepository,
) {
    @Cacheable("list")
    fun list(): List<String> = repository.findAll()
}

interface CacheExampleRepository {
    fun findAll(): List<String>
}

@Repository
class CacheExampleRepositoryImpl : CacheExampleRepository {
    override fun findAll(): List<String> {
        println("calling process")
        return listOf("a", "b", "c")
    }
}

@Component
class CacheExampleRunner(
    private val useCase: CacheExampleUseCase,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        println("call first ${useCase.list()}")
        println("call second ${useCase.list()}")
    }
}
