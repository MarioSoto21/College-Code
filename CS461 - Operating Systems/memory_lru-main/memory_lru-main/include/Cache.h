/*
 *  Multi2Sim
 *  Copyright (C) 2012  Rafael Ubal (ubal@ece.neu.edu)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

#ifndef MEMORY_CACHE_H
#define MEMORY_CACHE_H

#include <memory>
#include <list>
#include <string> // Include for std::string
#include <cassert>
#include "Misc.h"
#include <cmath>  // For log2
						//#include <bits/stdc++.h> 


namespace mem
{
	class Cache
	{
		public:
			/// Possible values for a cache block state
			enum BlockState
			{
				BlockInvalid,
				BlockValid,
				BlockModified
			};

			/// Cache block class
			class Block
			{
				// Only Cache needs to initialize fields
				friend class Cache;

				// Block tag
				unsigned tag = 0;

				// Transient tag assigned by NMOESI protocol
				unsigned transient_tag = 0;

				// Way identifier
				unsigned way_id = 0;

				// Block state
				BlockState state = BlockInvalid;

				unsigned int lru_bits;  // 2-bit counter to track LRU

				public:
				/// Constructor
				Block() {}

				/// Get the block tag
				unsigned getTag() const { return tag; }

				/// Get the way index of this block
				unsigned getWayId() const { return way_id; }

				/// Get the transient tag set in this block
				unsigned getTransientTag() const { return transient_tag; }

				/// Get the block state
				BlockState getState() const { return state; }

				/// Set new state and tag
				void setStateTag(BlockState state, unsigned tag)
				{
					this->state = state;
					this->tag = tag;
				}
			};

		private:
			// Name of the cache, used for debugging purposes
			std::string name;

			// Cache geometry
			unsigned num_sets;
			unsigned num_ways;
			unsigned num_blocks;
			unsigned block_size;
			int write_back; // 1 = write-back, 0 = write-through
			int write_allocate; // 1 = write-allocate, 0 = non-write-allocate

			// Mask used to get the block address
			unsigned block_mask;

			// Log base 2 of the block size
			int log_block_size;

			// Cache set
			class Set
			{
				// Only Cache needs to initialize fields
				friend class Cache;

				// List of blocks in LRU order
				// Replace 'List<Block>' with 'std::list<Block>' if appropriate
				std::list<Block> lru_list;

				// Position in Cache::blocks where the blocks start for this setBlock *blocks;
			};

			// Array of sets
			std::unique_ptr<Set[]> sets;

			// stats
			unsigned long num_hit = 0;
			unsigned long num_miss = 0;
			unsigned long num_evicted_blocks = 0;

		public:
			/// Constructor
			Cache(const std::string& name,
					unsigned num_sets,
					unsigned num_ways,
					unsigned block_size,
					unsigned replacement_policy,
					unsigned write_policy);

			/// Return a pointer to a cache block
			/**Block *getBlock(unsigned set_id, unsigned way_id) const
			  {
			  assert(misc::inRange(set_id, 0, num_sets - 1));
			  assert(misc::inRange(way_id, 0, num_ways - 1));
			  return &blocks[set_id * num_ways + way_id];
			  }**/

			/// Decode a physical address
			void DecodeAddress(unsigned long address, unsigned& set_id, unsigned& tag, unsigned& block_offset) const;

			/// Check whether an address is present in the cache
			/// Mark a block as last accessed as per the LRU policy if it is presented in the cache
			bool FindBlock(unsigned& set_id, unsigned& tag, unsigned& way_id) const;

			/// Set a new tag and state for a cache block
			void setBlock(unsigned set_id, unsigned way_id, unsigned tag, BlockState state);

			/// access to the cache memory
			bool Access(unsigned long address, int access_type);

			bool SetLRU(unsigned set_id, unsigned way_id);

			/// Return the way index of the block to be replaced
			unsigned ReplaceBlock(unsigned set_id);


			double getHitRatio() {
				return static_cast<double>(num_hit) / (num_hit + num_miss);
			}


			double getMissRatio() { 
				return static_cast<double>(num_miss) / (num_hit + num_miss);
			};

			unsigned long getEvectionRatio() { return num_evicted_blocks;};

			unsigned long getDataMovementSize() {
				return (num_miss + num_evicted_blocks) * block_size;
			};





	};

}  // namespace mem

#endif // MEMORY_CACHE_H
