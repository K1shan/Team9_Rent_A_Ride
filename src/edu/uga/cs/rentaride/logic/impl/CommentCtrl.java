package edu.uga.cs.rentaride.logic.impl;

import java.util.List;

import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;


public class CommentCtrl {
	
	private ObjectLayer objectLayer = null;
	private Comment modelComment = null;
	private Comment comment = null;
	private List<Comment> comments = null;
	
	public CommentCtrl( ObjectLayer objectLayer ){
        this.objectLayer = objectLayer;
    }
	
	public List<Comment> findComments( int id ) throws RARException{
		modelComment = objectLayer.createComment();
		if(id < 0)
			return objectLayer.findComment(null);
		
		modelComment.setId(id);
		return objectLayer.findComment(modelComment);
	}
	
}
