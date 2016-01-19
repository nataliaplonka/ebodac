DROP PROCEDURE IF EXISTS dropUniqueConstraint;

DELIMITER //
CREATE PROCEDURE dropUniqueConstraint()
BEGIN

IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.STATISTICS WHERE table_schema=DATABASE() AND table_name='BOOKING_APP_VISITSCHEDULEOFFSET' AND index_name='BOOKING_APP_VISITSCHEDULEOFFSET_U1') THEN

ALTER TABLE BOOKING_APP_VISITSCHEDULEOFFSET DROP INDEX BOOKING_APP_VISITSCHEDULEOFFSET_U1;

END IF;

END//

DELIMITER ;

CALL dropUniqueConstraint();

DROP PROCEDURE IF EXISTS dropUniqueConstraint;