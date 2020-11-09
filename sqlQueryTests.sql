select troops.name as troopname, troops.memo, 
		lords.name as lordname, 
        group_concat(arms.name, "-", arms.type) as arms
	from troops join lords on (troops.lordID = lords.lordID)
    join compositions on (troops.troopID = compositions.troopID)
    join arms on (compositions.armID = arms.armID)
	group by troops.troopID
    having troops.troopID = 1;

select * from troops where troopID = 2;

select * from compositions where troopID = 2;