package ch.hsr.osminabox.importing.listener;

import java.util.List;

import org.apache.log4j.Logger;
import ch.hsr.osminabox.db.entities.Relation;
import ch.hsr.osminabox.db.DBService;

/**
 * This listener is used for to handle delete calls for relations and
 * delegates the buffered relations to the database layer
 * 
 * @author rhof
 */
public class DeleteRelationListener extends EntityBufferListener<Relation> {

	private static Logger logger = Logger.getLogger(DeleteRelationListener.class);
	
	public DeleteRelationListener(DBService db, int bufferSize) {
		super(db, bufferSize);
	}

	@Override
	public void handleWakeupEvent(List<Relation> entitys) {
		if(logger.isDebugEnabled())
			logger.debug("Relation-Delete start");
		getDBService().deleteRelations(entitys);
		if(logger.isDebugEnabled())
			logger.debug("Relation-Delete stop");		
		logger.info("Processed RelationStack with buffersize: " + entitys.size());
	}

}