package com.github.gitmorozov.songlinks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gitmorozov.songlinks.entity.Song;

public interface SongRepository extends JpaRepository<Song, Long>  {

}
