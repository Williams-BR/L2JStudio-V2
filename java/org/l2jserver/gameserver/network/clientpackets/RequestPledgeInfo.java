/*
 * This file is part of the L2JServer project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2jserver.gameserver.network.clientpackets;

import org.l2jserver.gameserver.datatables.sql.ClanTable;
import org.l2jserver.gameserver.model.actor.instance.PlayerInstance;
import org.l2jserver.gameserver.model.clan.Clan;
import org.l2jserver.gameserver.network.serverpackets.PledgeInfo;

public class RequestPledgeInfo extends GameClientPacket
{
	private int clanId;
	
	@Override
	protected void readImpl()
	{
		clanId = readD();
	}
	
	@Override
	protected void runImpl()
	{
		final PlayerInstance player = getClient().getPlayer();
		final Clan clan = ClanTable.getInstance().getClan(clanId);
		if (player == null)
		{
			return;
		}
		
		if (clan == null)
		{
			return; // we have no clan data ?!? should not happen
		}
		player.sendPacket(new PledgeInfo(clan));
	}
}