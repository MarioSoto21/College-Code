/*
 * Author: Mario Soto
 * Date: 20 September 2024
 * Description: This program simulates a virtually indexed, virtually tagged cache with LRU replacement
 *              using Intel's Pin Tool for memory instrumentation.
 */
#include "Cache.h"
#include <iostream>

Cache::Cache(const std::string& name, unsigned num_sets, unsigned num_ways, unsigned block_size, int replacement_policy, int write_policy)
    : name(name), num_sets(num_sets), num_ways(num_ways), block_size(block_size), write_back(write_policy), write_allocate(replacement_policy)
{
    sets = std::make_unique<Set[]>(num_sets);
}

void Cache::DecodeAddress(unsigned address, unsigned& set_id, unsigned& tag, unsigned& block_offset) const {
    block_offset = address % block_size;
    set_id = (address / block_size) % num_sets;
    tag = (address / block_size) / num_sets;
}

bool Cache::FindBlock(unsigned& set_id, unsigned& tag) const {
    const Set& set = sets[set_id];
    for (const auto& block : set.lru_list) {
        if (block.getTag() == tag) {
            return true; // Cache hit
        }
    }
    return false; // Cache miss
}

void Cache::setBlock(unsigned set_id, unsigned way_id, unsigned tag, BlockState state) {
    Set& set = sets[set_id];
    for (auto& block : set.lru_list) {
        if (block.getWayId() == way_id) {
            block.setStateTag(state, tag);
            set.lru_list.splice(set.lru_list.begin(), set.lru_list, std::find(set.lru_list.begin(), set.lru_list.end(), block)); // Move block to front (LRU update)
            return;
        }
    }
}

unsigned Cache::ReplaceBlock(unsigned set_id) {
    Set& set = sets[set_id];

    // LRU block is at the back of the list
    Block& lru_block = set.lru_list.back();
    unsigned way_id = lru_block.getWayId();

    // Remove the LRU block from the list
    set.lru_list.pop_back();

    // Insert new block at the front of the list
    Block new_block;
    set.lru_list.push_front(new_block);

    return way_id;
}

void Cache::Access(unsigned address, int access_type) {
    unsigned set_id, tag, block_offset;
    DecodeAddress(address, set_id, tag, block_offset);

    if (FindBlock(set_id, tag)) {
        std::cout << "Cache hit at set " << set_id << ", tag " << tag << std::endl;
    } else {
        std::cout << "Cache miss at set " << set_id << ", tag " << tag << std::endl;
        unsigned way_id = ReplaceBlock(set_id);
        setBlock(set_id, way_id, tag, BlockModified); // Assuming all writes modify the block
    }
}
