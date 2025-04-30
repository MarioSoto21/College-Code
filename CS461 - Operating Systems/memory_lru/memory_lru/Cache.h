/*
 * Author: Mario Soto
 * Date: 20 September 2024
 * Description: This program simulates a virtually indexed, virtually tagged cache with LRU replacement
 *              using Intel's Pin Tool for memory instrumentation.
 */
#ifndef MEMORY_CACHE_H
#define MEMORY_CACHE_H

#include <memory>
#include <list>
#include <string>

class Cache {
public:
    enum BlockState {
        BlockInvalid,
        BlockModified
    };

    class Block {
        friend class Cache;
        unsigned tag = 0;
        unsigned way_id = 0;
        unsigned transient_tag = 0;
        BlockState state = BlockInvalid;

    public:
        Block() {}

        unsigned getTag() const { return tag; }
        unsigned getWayId() const { return way_id; }
        BlockState getState() const { return state; }

        void setStateTag(BlockState state, unsigned tag) {
            this->state = state;
            this->tag = tag;
        }
    };

private:
    std::string name;
    unsigned num_sets;
    unsigned num_ways;
    unsigned block_size;
    int write_back;
    int write_allocate;

    class Set {
        friend class Cache;
        std::list<Block> lru_list;
    };

    std::unique_ptr<Set[]> sets;

public:
    Cache(const std::string& name, unsigned num_sets, unsigned num_ways, unsigned block_size, int replacement_policy, int write_policy);

    void DecodeAddress(unsigned address, unsigned& set_id, unsigned& tag, unsigned& block_offset) const;
    bool FindBlock(unsigned& set_id, unsigned& tag) const;
    void setBlock(unsigned set_id, unsigned way_id, unsigned tag, BlockState state);
    unsigned ReplaceBlock(unsigned set_id);
    void Access(unsigned address, int access_type);
};

#endif
