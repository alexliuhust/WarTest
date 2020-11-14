select troop.name as troopname, troop.memo, 
		lord.name as lordname, 
        group_concat(arm.name, "-", arm.type) as arms
	from troop join lord on (troop.lordID = lord.lordID)
    join composition on (troop.troopID = composition.troopID)
    join arm on (composition.armID = arm.armID)
    where troop.userID = 1
	group by troop.troopID;

select * from troop where troopID = 2;

select * from composition where troopID = 2;