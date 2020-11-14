select t.name as troopname, t.memo, 
		l.name as lordname, 
        group_concat(a.name, "-", a.type) as arms
	from troop t join lord l on (t.lordID = l.lordID)
    join composition c on (t.troopID = c.troopID)
    join arm a on (c.armID = a.armID)
    where t.userID = 1 and t.troopID = 1
	group by t.troopID;

select * from troop where troopID = 2;

select * from composition where troopID = 2;