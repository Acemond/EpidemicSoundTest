package com.epidemicsound.test.tracker;

import org.springframework.data.repository.CrudRepository;

public interface TracksRepository extends CrudRepository<InternalTrack, String> {
}
