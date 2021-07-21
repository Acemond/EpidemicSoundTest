package com.epidemicsound.test;

import org.springframework.data.repository.CrudRepository;

public interface TracksRepository extends CrudRepository<InternalTrack, String> {
}
