package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private List<TimeEntry> timeEntries = new ArrayList<>();
    private Long startId = 1L;
    
    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry timeEntryToCreate = new TimeEntry(
                startId,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        this.timeEntries.add(timeEntryToCreate);
        startId++;
        return timeEntryToCreate;
    }

    public TimeEntry find(Long id) {
            List<TimeEntry> filteredList = this.timeEntries
                .stream()
                .filter(te -> te.getId() == id)
                .collect(Collectors.toList());

            return filteredList.size() > 0 ? filteredList.get(0) : null;
    }

    public List<TimeEntry> list() {
        return this.timeEntries;
    }

    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry timeEntryToUpdate = find(id);
        if(timeEntryToUpdate != null) {
            delete(id);
            timeEntryToUpdate = new TimeEntry(
                    id,
                    timeEntry.getProjectId(),
                    timeEntry.getUserId(),
                    timeEntry.getDate(),
                    timeEntry.getHours()
            );
            this.timeEntries.add(timeEntryToUpdate);
        }
        return timeEntryToUpdate;
    }

    public void delete(Long id) {
        TimeEntry timeEntryToRemove = find(id);
        this.timeEntries.remove(timeEntryToRemove);
    }
}
