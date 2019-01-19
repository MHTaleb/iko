/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iko.service.implementation;

import com.iko.iko.EntityManagerConfig;
import com.iko.iko.domain.Movie;
import com.iko.iko.domain.Note;
import com.iko.iko.domain.User;
import com.iko.iko.repository.MovieJpaController;
import com.iko.iko.repository.NoteJpaController;
import com.iko.service.MovieNoteService;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovieNoteServiceImpl implements MovieNoteService {

    MovieJpaController controller = new MovieJpaController(EntityManagerConfig.getEmf());
    NoteJpaController noteJpaController = new NoteJpaController(EntityManagerConfig.getEmf());
  
    @Override
    public double updateNote(Long movieId, double note) {
        
        double finalNote = 0;
        
        User currentUser = ConnectionServiceImpl.getConnectedUser();
        Movie currentMovie = controller.findMovie(movieId);
        
        Predicate<? super Note> userNoteFilter = currNote -> {
            return currNote.getUser().getId().longValue() == currentUser.getId().longValue() ;
        };
        Optional<Note> searchedNote = currentMovie.getNotes().stream().filter(userNoteFilter).findFirst();
        
        if(searchedNote.isPresent()){
            Note editNote = searchedNote.get();
            editNote.setValeur(note);
            try {
                noteJpaController.edit(editNote);
            } catch (Exception ex) {
                Logger.getLogger(MovieNoteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            final Note addedNote = new Note();
            addedNote.setUser(currentUser);
            addedNote.setValeur(note);
            noteJpaController.create(addedNote);
            currentMovie.getNotes().add(addedNote);
            try {
                controller.edit(currentMovie);
            } catch (Exception ex) {
                Logger.getLogger(MovieNoteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        finalNote = currentMovie.getNotes().stream().map((note1) -> note1.getValeur()).reduce(finalNote, (accumulator, _item) -> accumulator + _item);
        finalNote/=currentMovie.getNotes().size();
        
        return finalNote;
    }

}
