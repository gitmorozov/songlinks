package com.github.gitmorozov.songlinks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gitmorozov.songlinks.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long>  {

}
