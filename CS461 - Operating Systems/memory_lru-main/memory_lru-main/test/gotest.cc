#include <gtest.h>
#include "../include/Cache.h" // Replace with your cache class header

class CacheTest : public ::testing::Test {
protected:
    Cache cache; // Assuming you have a Cache class

    void SetUp() override {
        // Initialize cache if necessary, e.g., setting cache parameters
    }
};

Cache* cache = NULL;

TEST(cacheTest, AssertCurrentMemoryAccessestoYieldInMisses)
{
    unsigned long address = 140724845116824;
    unsigned set_id, tag, block_offset;
    
    cache = new Cache(1, 16 * 1024,64, 4);
    cache->DecodeAddress(address, set_id, tag, block_offset);
    unsigned short int result = 0;
    result = cache->logMemoryAddress("0x00005fffffffd8e8", 8, 'W');
    ASSERT_TRUE(result == false);
    result = cache->logMemoryAddress("0x00006fffffffd8e8", 8, 'W');
    ASSERT_TRUE(result == false);
    result = cache->logMemoryAddress("0x00007fffffffd8e8", 8, 'R');
    ASSERT_TRUE(result == false);
    result = cache->logMemoryAddress("0x00008fffffffd8e8", 8, 'R');
    ASSERT_TRUE(result == false);
}

// Test for DecodeAddress
TEST_F(CacheTest, DecodeAddressTest) {
    unsigned long address = 140724845116824;
    unsigned set_id, tag, block_offset;
    
    cache.DecodeAddress(address, set_id, tag, block_offset);
    
    // Expected values depend on your cache configuration (e.g., block size, cache size)
    EXPECT_EQ(set_id, 1);
    EXPECT_EQ(tag, 1);
    EXPECT_EQ(block_offset, 1);
}

// // Test for FindBlock
// TEST_F(CacheTest, FindBlockTest) {
//     unsigned set_id = 1, tag = 10, way_id;
    
//     // Check that block is not found initially
//     EXPECT_FALSE(cache.FindBlock(set_id, tag, way_id));
    
//     // Insert a block and try to find it
//     cache.setBlock(set_id, 0, tag, BlockState::VALID);
//     EXPECT_TRUE(cache.FindBlock(set_id, tag, way_id));
//     EXPECT_EQ(way_id, 0);  // Ensure the correct way was found
// }

// // Test for setBlock
// TEST_F(CacheTest, SetBlockTest) {
//     unsigned set_id = 1, way_id = 0, tag = 10;
//     BlockState state = BlockState::VALID;
    
//     // Set block
//     cache.setBlock(set_id, way_id, tag, state);
    
//     unsigned found_way_id;
//     EXPECT_TRUE(cache.FindBlock(set_id, tag, found_way_id));
//     EXPECT_EQ(found_way_id, way_id);
// }

// // Test for Access
// TEST_F(CacheTest, AccessTest) {
//     unsigned long address = 0xABCDEF;
//     int access_type = 0;  // Assume some type of access, like READ or WRITE
    
//     cache.Access(address, access_type);
    
//     unsigned set_id, tag, block_offset;
//     cache.DecodeAddress(address, set_id, tag, block_offset);
    
//     unsigned way_id;
//     EXPECT_TRUE(cache.FindBlock(set_id, tag, way_id));  // Access should bring it into cache
// }

// // Test for SetLRU
// TEST_F(CacheTest, SetLRUTest) {
//     unsigned set_id = 1, way_id = 0;
    
//     EXPECT_TRUE(cache.SetLRU(set_id, way_id));  // Check if LRU is updated correctly
//     // Add further checks based on your cache's LRU implementation
// }

// // Test for ReplaceBlock
// TEST_F(CacheTest, ReplaceBlockTest) {
//     unsigned set_id = 1;
    
//     unsigned way_to_replace = cache.ReplaceBlock(set_id);
    
//     // Insert multiple blocks and ensure the least recently used block is replaced
//     cache.setBlock(set_id, 0, 5, BlockState::VALID);
//     cache.setBlock(set_id, 1, 6, BlockState::VALID);
//     cache.SetLRU(set_id, 0);  // Mark way 0 as recently used
    
//     way_to_replace = cache.ReplaceBlock(set_id);
//     EXPECT_EQ(way_to_replace, 1);  // Way 1 should be replaced if it's LRU
// }
