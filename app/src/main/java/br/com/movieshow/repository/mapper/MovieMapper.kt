package br.com.movieshow.repository.mapper

import br.com.movieshow.domain.Movie
import br.com.movieshow.dto.MovieDTO
import org.mapstruct.Mapper

@Mapper
interface MovieMapper {

    fun toDTO(domain: Movie): MovieDTO

    fun toDomain(dto: MovieDTO): Movie
}